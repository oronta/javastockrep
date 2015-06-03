package com.oroninc.stockproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.algo.service.ServiceManager;

import com.oroninc.stockproject.service.PortfolioManager;

@SuppressWarnings("unused")
public class InitServlet extends javax.servlet.http.HttpServlet {
	

	@Override
public void init() throws ServletException {
		PortfolioManager pm =new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
		super.init();
			
	}
	

}
