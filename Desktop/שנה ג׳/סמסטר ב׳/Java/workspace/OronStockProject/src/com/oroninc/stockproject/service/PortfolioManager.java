package com.oroninc.stockproject.service;

import java.util.Calendar;
import java.util.Date;

import com.oroninc.stockproject.model.Portfolio;
import com.oroninc.stockproject.Stock;
import com.oroninc.stockproject.StockDetailsServlet;



public class PortfolioManager {

		Portfolio portfolio;
		
		
		public Portfolio getPortfolio(){
							
			Portfolio portfolio = new Portfolio();
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 15, 11, 0, 0, 0); // Change to the assign date
			Date date1= cal.getTime();
		
			Stock PIH = new Stock("PIH", 13.1f, 12.4f, date1 );
			portfolio.addStock(PIH);

			Stock AAL = new Stock("AAL", 5.78f, 5.5f, date1);			
			portfolio.addStock(AAL);

			Stock CAAS = new Stock("CAAS", 32.2f, 31.5f, date1);
			portfolio.addStock(CAAS);
			
			return portfolio;			
		}
	
	
	
	
}
