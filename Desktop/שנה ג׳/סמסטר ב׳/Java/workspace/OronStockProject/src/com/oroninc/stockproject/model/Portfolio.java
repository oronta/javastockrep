/* Class for portfolio objects */

package com.oroninc.stockproject.model;

import com.google.api.server.spi.Constant;

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
	public void addStock(Stock stock){			
		
		int i=0;
		
		while(i<portfolioSize && !stocks[i].getSymbol().equals(stock.getSymbol())){
			i++;
		}
		if(i == portfolioSize && portfolioSize<MAX_SIZE_PORTFOLIO ){
			this.stocks[this.portfolioSize]=(StockInterface) stock;
			stock.setstockQuantity(0);
			portfolioSize++;			
		}
		else if(portfolioSize==MAX_SIZE_PORTFOLIO){
			System.out.println("Can't add new stock, a portfolio can only have " +MAX_SIZE_PORTFOLIO+ "stocks");
		}
		else{
			return;
		}
	}


	public boolean removeStock(String symbol){ // Removes a stock from the portfolio's stocks array
		
		for(int i=0; i<portfolioSize; i++){
			if(stocks[i].getSymbol().equals(symbol)){
				sellStock(stocks[i].getSymbol(),1);
				stocks[i]=stocks[portfolioSize-1];
				stocks[portfolioSize-1]=null;
				portfolioSize = portfolioSize-1;
				System.out.println("The Stock has been removed");
				return true;
			}
		}
		System.out.println("There's no such stock in the Portfolio");
		return false;
	}
				
	public boolean sellStock(String symbol, int quant){ // Sells a stock if authorized
		
		
		for(int i=0; i<portfolioSize; i++){
			
			if(stocks[i].getSymbol().equals(symbol) && ((Stock) stocks[i]).getstockQuantity()>0 && ((Stock) stocks[i]).getstockQuantity()<=quant){
				int newquant = ((Stock) stocks[i]).getstockQuantity()-quant; // calc new quantity
				((Stock) stocks[i]).setstockQuantity(newquant);
				float sum; // Amount that needs to be added to balance
				sum = ((Stock) stocks[i]).getstockQuantity()*stocks[i].getBid();
				updateBalance(sum);
				System.out.println("The Stock named" +symbol+  "has been sold");
				return true;
			}
			else if(stocks[i].getSymbol().equals(symbol) && ((Stock) stocks[i]).getstockQuantity()==0){
				System.out.println("You do not have enough credit to sell" +quant+ "amount of stocks");
				return false;
			}
			else if(stocks[i].getSymbol().equals(symbol) && quant==-1){
				float saleSum;
				saleSum = ((Stock) stocks[i]).getstockQuantity()*stocks[i].getBid();
				updateBalance(saleSum);
				((Stock) stocks[i]).setstockQuantity(0);
				return true;
			}
		}
		System.out.println("The Stock named" +symbol+  "has not been found in the portfolio");
		return false;		
	}
	
	public boolean buyStock(Stock stockToPurchase, int quant) // Buy a stock
	{
		boolean stockIsInPortfolio = false;
		boolean buySucceed = false;

		for (int i=0; i < portfolioSize; i++) // check all of the portfolio
		{
			if (stocks[i].getSymbol().equals(stockToPurchase.getSymbol())) // stock to purchase is already in the portfolio
			{
				stockIsInPortfolio = true;
				
				if (quant == -1) 
				{
					while (balance > stockToPurchase.getAsk())
					{			
						quant--;
						((Stock) stocks[i]).setstockQuantity(((Stock) stocks[i]).getstockQuantity()+1);
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
					((Stock) stocks[i]).setstockQuantity(((Stock) stocks[i]).getstockQuantity()+quant);
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
	return buySucceed;
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

	public Stock findStock(String stockSymbol){
		int index = 0;
		if(stockSymbol.equals(null)){
			System.out.print("There is no stock with this name");
			return null;
		}
		else if(this.portfolioSize == 0){
			return null;
		}
		else{
			for(index = 0; index < this.portfolioSize; index++){
				if(this.stocks[index].getSymbol().equals(stockSymbol)){
				break;
				}
			}
		}
		return (Stock) stocks[index];	
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
