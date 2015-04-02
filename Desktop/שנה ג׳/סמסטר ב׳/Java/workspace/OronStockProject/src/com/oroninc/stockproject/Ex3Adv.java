package com.oroninc.stockproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Ex3Adv extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3000386414113144704L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");
			
			// 1 - Circle Area
			double area, Radius;
			Radius = 50;
			area = Math.PI*Radius*Radius;
			String line1 = new String("<h1>Calculation 1: Area of circle with radius "+Radius+" is "+area+ " square-cm ");
			
			// 2 - Triangle's opposite 
			double Bdeg, hypoLength, oppLength;
			Bdeg = 30; // in degrees
			hypoLength = 50;
			double RadB = Math.toRadians(Bdeg); // converting to Radians
			double sinRadB= Math.sin(RadB);
			oppLength = sinRadB * hypoLength;
			
			String line2 = new String("<h1> Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: " +oppLength+ " cm ");
			
			// 3 - Power of 20^13
			double base, exp, resPow;
			base = 20;
			exp = 13;
			resPow = Math.pow(base, exp);
			
			String line3 = new String("<h1> Calculation 3: Power of " +base+ " with exp of " +exp+ " is " +resPow );
			
			/////////////////////
			
			String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
			resp.getWriter().println(resultStr);
			
	}
}	