
// Class for Stock objects
package com.oroninc.stockproject.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.algo.model.StockInterface;

import com.oroninc.stockproject.model.Portfolio.ALGO_RECOMMENDATION;

public class Stock implements StockInterface {
	
	private String symbol; 
	private float ask, bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
			
	public Stock() {
		
		this.symbol = null;
		this.ask = 0;
		this.bid = 0;
		this.date = null;
		this.stockQuantity = 0;
	}
	public Stock(String Symbol, float ask, float bid, Date date){ // CONST THAT WILL MAKE ACCESSING STOCK IN AN EASIER WAY
		this.symbol = Symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
	}
	
	/* Copy const*/
	public Stock(Stock stock){ 
		this(new String(stock.getSymbol()), stock.getAsk(),stock.getBid(), new Date(stock.getDate().getTime())); // לעשות ככהההההה
		
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
	public void setDate(Date time) {
		this.date = time;
	}
	public void setDate(long time) {
		this.date = new Date(time);
	}
	
	public int getstockQuantity () {
		return stockQuantity;
	}
	public void setstockQuantity (int stockQuantity) {
		this.stockQuantity=stockQuantity;
	}	
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation=recommendation;
			
	}
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
			
	}	
	
	
	public String getHtmlDescription() { // Html output of a stock's details
		
		DateFormat df= new SimpleDateFormat("MM/dd/yyyy");
		String dateStrng = df.format(getDate());
		String StockDetails = "<b> Symbol: </b>" +getSymbol() + "<b> Ask: </b>" +getAsk() + "<b> Bid: <b/> " +getBid() + "<b> Date: <b/> " +dateStrng;
		
		
		return StockDetails;
	
	}
	
	
}




