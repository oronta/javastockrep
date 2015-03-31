package com.oroninc.stockproject;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class OronStockProjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		int num1, num2, num3, result;
		num1 = 4;
		num2 = 3;
		num3 = 7;
		result = num3*(num1+num2);
		String resultStr = new String("<h1>Result of num3*(num1+num2)="+result+"");
		
		resp.getWriter().println(resultStr);
	}
}
