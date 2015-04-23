
package com.oroninc.stockproject;

import java.util.Calendar;
import java.util.Date;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class StockDetailsServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			resp.setContentType("text/html");
			
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 15, 11, 0, 0, 0); // Change to the assign date
			Date date1= cal.getTime();
			
			
			Stock PIH = new Stock("PIH", 13.1f, 12.4f, date1 );
			Stock AAL = new Stock("AAL", 5.78f, 5.5f, date1);			
			Stock CAAS = new Stock("CAAS", 32.2f, 31.5f, date1);
			
			resp.getWriter().println("<p>");
			resp.getWriter().println(PIH.getHtmlDescription());
			resp.getWriter().println("<p>");
			resp.getWriter().println(AAL.getHtmlDescription());
			resp.getWriter().println("<p>");
			resp.getWriter().println(CAAS.getHtmlDescription());

	}
	
}

