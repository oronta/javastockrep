package com.oroninc.stockproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oroninc.stockproject.model.Portfolio;
import com.oroninc.stockproject.service.PortfolioManager;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		PortfolioManager portfolioManager = new PortfolioManager();
		Portfolio portfolio1 = portfolioManager.getPortfolio();
		Portfolio portfolio2 = new Portfolio(portfolio1);
		portfolio2.setTitle("Portfolio #2");
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	
		portfolio1.rmvStock("PIH");
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
		
		portfolio2.getStocks()[portfolio2.getPortfolioSize()-1].setBid((float)55.55);
		
		resp.getWriter().println(portfolio1.getHtmlString());
		resp.getWriter().println(portfolio2.getHtmlString());
	}
	



}