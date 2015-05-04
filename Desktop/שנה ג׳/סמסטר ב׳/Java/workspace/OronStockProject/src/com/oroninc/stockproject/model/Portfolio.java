package com.oroninc.stockproject.model;

import com.google.api.server.spi.Constant;
import com.oroninc.stockproject.Stock;

public class Portfolio {

	private String title;
	public static final int  MAX_SIZE_PORTFOLIO=5;
	Stock stocks[];
	int portfolioSize=0;	
		
	
	public Portfolio(){
		stocks = new Stock[MAX_SIZE_PORTFOLIO];
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

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
}
