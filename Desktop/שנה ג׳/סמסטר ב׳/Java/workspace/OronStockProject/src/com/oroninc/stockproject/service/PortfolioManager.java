/* Creats a new portfolio */
package com.oroninc.stockproject.service;

import java.util.Calendar;
import java.util.Date;

import com.oroninc.stockproject.model.Portfolio;
import com.oroninc.stockproject.model.Stock;
import com.oroninc.stockproject.model.*;


public class PortfolioManager {

		
		public Portfolio getPortfolio(){
							
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 15, 11, 0, 0, 0); // Change to the assign date
			Date date1= cal.getTime();
			Date date2= cal.getTime();
			Date date3= cal.getTime();
			
			String portflioTitle = "Portfolio #1";
			Stock PIH = new Stock("PIH", 13.1f, 12.4f, date1 );
			Stock AAL = new Stock("AAL", 5.78f, 5.5f, date2);			
			Stock CAAS = new Stock("CAAS", 32.2f, 31.5f, date3);
			Stock[] stkArr = {PIH,AAL,CAAS};
			int numOfStcks = 3;
			
			Portfolio portfolio = new Portfolio(portflioTitle,stkArr,numOfStcks);
			
			return portfolio;
		}
	
	
	
}
