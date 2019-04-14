package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		Class clazz = this.getClass();
		String type = request.getParameter("type");
		try {
			Method method = clazz.getDeclaredMethod(type,HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request , response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
