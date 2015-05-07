/* Class for portfolio objects */

package com.oroninc.stockproject.model;

import com.google.api.server.spi.Constant;

import java.util.Date;
public class Portfolio {

	private String title;
	public static final int  MAX_SIZE_PORTFOLIO=5;
	private Stock[] stocks = new Stock[MAX_SIZE_PORTFOLIO];
	int portfolioSize;	

	
	
	public Portfolio(String title,Stock[] stocks, int portfolioSize){ /* const */
		this.title = title;
		this.stocks = stocks;
		this.portfolioSize = portfolioSize;
	}
	
	public Portfolio(Portfolio portfolioCpy){ /* copy constructor */
		this.title = portfolioCpy.getTitle();
		
		for(int i=0;i<=portfolioCpy.getPortfolioSize();i++){
			String symbol = portfolioCpy.stocks[i].getSymbol();
			float ask = portfolioCpy.stocks[i].getAsk();
			float bid = portfolioCpy.stocks[i].getBid();
			Date date = portfolioCpy.stocks[i].getDate();
			Stock cpystock = new Stock(symbol, ask, bid, date);
			this.stocks[i] = cpystock;
		}
		this.portfolioSize=portfolioCpy.getPortfolioSize();
		
	}
	
	/* Adding a new stock to the Portflio */
	public void addStock(Stock stock){			

		if(stock != null && portfolioSize<MAX_SIZE_PORTFOLIO){
			this.stocks[portfolioSize]=stock;
			portfolioSize++;
		}
			else{
				System.out.println("The portfolio is full");
			}
		}
	
	/* Removing a stock from portfolio */
	public void rmvStock(String delStock){

			for(int i=0; i<=portfolioSize; i++){
				if(stocks[i].getSymbol().equals(delStock)){
					stocks[i]=null;
				}
			}
		}
				

	
	/* return the stocks array */
	public Stock[] getStocks(){
		return stocks;
	}

	public String getHtmlString(){
			String portfolio = "<h1>Portfolio Title: <h1>" +getTitle()+"<br>Maximum Stocks in Portfolio : </br>" +getMaxSizePortfolio();
			
			/* adding stocks array to string */
			for(int i=0; i<portfolioSize;i++){
				portfolio+=stocks[i].getHtmlDescription() +"<br>";
			}
			return portfolio;
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
	
}
