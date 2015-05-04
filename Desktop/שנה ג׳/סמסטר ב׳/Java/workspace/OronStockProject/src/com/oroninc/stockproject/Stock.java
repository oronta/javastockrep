

package com.oroninc.stockproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {
	
	private String symbol; 
	private float ask, bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;
	public static final int BUY=0, SELL=1, REMOVE = 2, HOLD = 3;
			
	public Stock(String Symbol, float ask, float bid, Date date){ // CONST THAT WILL MAKE ACCESSING STOCK IN AN EASIER WAY
		this.symbol = Symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
	}
	
	// Getter's & Setter's
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}	
	
	public String getHtmlDescription() { // לשנות כמו ששניתי בסרבלט
		
		DateFormat df= new SimpleDateFormat("MM/dd/yyyy");
		String dateStrng = df.format(getDate());
		String StockDetails = "<b> Symbol: </b>" +getSymbol() + "<b> Ask: </b>" +getAsk() + "<b> Bid: <b/> " +getBid() + "<b> Date: <b/> " +dateStrng;
		
		
		return StockDetails;
	
	}
	
	
}




