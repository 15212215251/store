package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// TODO Auto-generated method stub
			Class clazz = this.getClass();
			String m = request.getParameter("method");
			
			if (m == null) {
				m = "index";
			}
			
			Method method = clazz.getMethod(m, HttpServletRequest.class, HttpServletResponse.class);
			String string = (String) method.invoke(this, request, response);
			if (string != null) {
				request.getRequestDispatcher(string).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
