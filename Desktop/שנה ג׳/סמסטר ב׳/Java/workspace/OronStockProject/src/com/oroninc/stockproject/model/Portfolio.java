/* Class for portfolio objects */

package com.oroninc.stockproject.model;

import com.google.api.server.spi.Constant;
import com.oroninc.stockproject.exception.BalanceException;
import com.oroninc.stockproject.exception.PortfolioFullException;
import com.oroninc.stockproject.exception.StockAlreadyExistsException;
import com.oroninc.stockproject.exception.StockNotExistException;

import java.util.Date;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
public class Portfolio implements PortfolioInterface{

	private String title;
	public static final int  MAX_SIZE_PORTFOLIO=5;
	private StockInterface[] stocks;
	int portfolioSize;
	private float balance;
	
	public enum ALGO_RECOMMENDATION{
		BUY, SELL, REMOVE, HOLD
	}
	
	
	public Portfolio() // New empty portfolio
	{
	    this.title = "empty portfolio";
	    this.stocks = new StockInterface[MAX_SIZE_PORTFOLIO];
	    this.portfolioSize = 0;
	    this.balance = 0;
	}	
		
	
	/*public Portfolio(String title,Stock[] stocks, int portfolioSize){ 
		this.title = title;
		this.stocks = StockInterface;
		this.portfolioSize = portfolioSize;
	} */
	
	public Portfolio(StockInterface[] stocksArray) {
		this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_SIZE_PORTFOLIO];
		this.portfolioSize = stocksArray.length;
		cpyArray(stocksArray, stocks);
		this.balance = 0;
	}

	public Portfolio(String string) {
		this.title = string;
		this.stocks = new StockInterface[MAX_SIZE_PORTFOLIO];
		this.portfolioSize = 0;
		this.balance = 0;
	}	
	
	public Portfolio(Portfolio portfolioCpy){ /* copy constructor */
		this.title = portfolioCpy.getTitle();
		
		for(int i=0;i<=portfolioCpy.getPortfolioSize();i++){
			String symbol = portfolioCpy.stocks[i].getSymbol();
			float ask = portfolioCpy.stocks[i].getAsk();
			float bid = portfolioCpy.stocks[i].getBid();
			Date date = portfolioCpy.stocks[i].getDate();
			Stock cpystock = new Stock(symbol, ask, bid, date);
			this.stocks[i] = (StockInterface) cpystock;
		}
		this.portfolioSize=portfolioCpy.getPortfolioSize();
		
	}
	
	private void cpyArray(StockInterface[] oldStockInterfaces, StockInterface[] newStockInterfaces ){

		for(int i = 0; i<this.portfolioSize; i++){
			newStockInterfaces[i]= (StockInterface) new Stock ((Stock)oldStockInterfaces[i]);

		}
	}	
	
	
	
	public void updateBalance(float amount){
		
		if(amount==0){
			System.out.println("The balance hasn't changed");
		}
		else if(amount<0 && balance+(amount)<0 ){
			System.out.println("You don't have enougt credit to take out" +amount);			
		}
		else{
			this.balance=amount+balance;
			System.out.println("Your updated balance is: " +balance);
		}
		
	}
	
		
	/* Adding a new stock to the Portflio */
	public void addStock(Stock stock) throws PortfolioFullException, StockAlreadyExistsException, StockNotExistException{			
		
		if (stock == null){
			System.out.println("error");
		}
		else if(this.portfolioSize == MAX_SIZE_PORTFOLIO){
			throw new PortfolioFullException(MAX_SIZE_PORTFOLIO);
		}

		else 
		{
			throw new StockAlreadyExistsException("Already exists");
		}
	}

	public void removeStock(String symbol)throws StockNotExistException { // Removes a stock from the portfolio's stocks array
		
		if(symbol.equals(null)){
			throw new StockNotExistException();
		}
			else{
				int index= findStock(symbol);
				if(index < this.portfolioSize){
				if(((Stock) this.stocks[index]).getstockQuantity() != 0 ){
				sellStock(symbol, -1);
				this.stocks[index] = this.stocks[this.portfolioSize-1];
				this.stocks[this.portfolioSize-1] = null;
				this.portfolioSize--;
				}
				else{
						this.stocks[index] = this.stocks[this.portfolioSize-1];
						this.stocks[this.portfolioSize-1] = null;
						this.portfolioSize--;
				}
				}
			}
	}
	
				
	public void sellStock(String symbol, int quant)  throws StockNotExistException, PortfolioFullException{ // Sells a stock if authorized
		
		boolean Exists = false;
		if (symbol == null || quant < -1) { 
			throw new StockNotExistException("Invalid input");
		}				
		for (int i = 0; i < this.portfolioSize && Exists == false; i++) {
			if (symbol.equals(stocks[i].getSymbol())) {
				Exists = true;
				if (((Stock) this.stocks[i]).getstockQuantity() - quant < 0) {
					throw new PortfolioFullException(i);

				} else if (quant == -1) {
					this.balance += ((Stock) this.stocks[i]).getstockQuantity()
							* this.stocks[i].getBid();
					((Stock) this.stocks[i]).setstockQuantity(0);
					System.out.println("All stocks of " + symbol
							+ " were sold!");
					}
			}
		}
		if (Exists == false) {
			throw new StockNotExistException(
					"The stock you're trying to sell doesn't appear in portfolio");
		}		
	}
			
	
	public void buyStock(Stock stockToPurchase, int quant) throws BalanceException, PortfolioFullException, StockAlreadyExistsException, StockNotExistException // Buy a stock
	{
		boolean stockIsInPortfolio = false;
		boolean buySucceed = false;

		int i = 0;
		if (stockToPurchase == null || quant < -1) {
			throw new StockNotExistException("No null stock OR invalid quantity!");
		}
		
		
		
		for (int i1=0; i1 < portfolioSize; i1++) // check all of the portfolio
		{
			if (stocks[i1].getSymbol().equals(stockToPurchase.getSymbol())) // stock to purchase is already in the portfolio
			{
				stockIsInPortfolio = true;
				
				if (quant == -1) 
				{
					while (balance > stockToPurchase.getAsk())
					{			
						quant--;
						((Stock) stocks[i1]).setstockQuantity(((Stock) stocks[i1]).getstockQuantity()+1);
						this.balance = this.balance - stockToPurchase.getAsk();
					}
					buySucceed = true;
				}
				else if ((stockToPurchase.getAsk() * quant) > this.balance) // no
				{			
						System.out.print("Not enough credit in order to complete purchase");					
				}
				else 
				{
					((Stock) stocks[i1]).setstockQuantity(((Stock) stocks[i1]).getstockQuantity()+quant);
					this.balance -= stockToPurchase.getAsk() * quant;	
					buySucceed = true;
				}
			}
		}		
		if (stockIsInPortfolio == false) // stock doesn't exist in portfolio
		{
			if (portfolioSize == MAX_SIZE_PORTFOLIO) // portfolio is full
			{
				System.out.print("Portfolio is full, can't complete the purchase");				
			}
			else if (this.balance > stockToPurchase.getAsk() * quant) // there is space, there are funds
			{
				stockToPurchase.setstockQuantity(quant); 
				this.balance -= (stockToPurchase.getAsk() * quant);
				addStock(stockToPurchase);
				buySucceed = true;	
			}
			else 
			{
				System.out.print("Not enough credit to complete purchase");					
				buySucceed = false;	
			}
		}
		else if (i == MAX_SIZE_PORTFOLIO) { 
			throw new PortfolioFullException(i);
		}

	}

	
	/* return the stocks array */
	public StockInterface[] getStocks(){
		return stocks;
	}

	public String getHtmlString(){
			String portfolio = "<h1>Portfolio Title: <h1>" +getTitle()+"<br>Maximum Stocks in Portfolio : </br>" +getMaxSizePortfolio();
			
			/* adding stocks array to string */
			for(int i=0; i<portfolioSize;i++){
				portfolio+=((Stock) stocks[i]).getHtmlDescription() +"<br>";
			}
			portfolio = portfolio + "<br>" + "Total Portfolio Value: " + getTotalValue() + "<br>" + "Total Stocks Value: " + this.getStocksValue() + "<br>"+ "Balance: " + getBalance();
			return portfolio;			
			
	}

	public StockInterface findStock(String findSymbol){

		int i = 0;
		if (findSymbol == null) {
			return null;
		}
			for(int i = 0; i< this.portfolioSize; i++){
				if(findSymbol.equals(this.stocks[i].getSymbol())){
					return this.stocks[i];
				}
			}
			return null;
		}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static int getMaxSizePortfolio() {
		return MAX_SIZE_PORTFOLIO;
	}
	
	public int getPortfolioSize(){
		return portfolioSize;
	}
	
	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	public double getStocksValue()
	{
		double sumVal = 0;
		for (int i=0; i < portfolioSize; i++)
		{
			sumVal = sumVal + (((Stock) this.stocks[i]).getstockQuantity() * this.stocks[i].getBid());
		}
		return sumVal;
	}
	
	public double getTotalValue()
	{
		return this.getStocksValue()+getBalance();
	}
	
	public float getBalance()
	{
		return this.balance;
	}
	
}
