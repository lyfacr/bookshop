package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

/**
 * 
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private BookService service = new BookServiceImpl();
  
	protected void getPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		
		Page<Book> page = service.getPage(pageNumber, size);
		
		page.setPath("BookClientServlet?type=getPage");
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
		
		
	}
	
	protected void getPageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		int size = 4;
		
		Page<Book> page = service.getPageByPrice(pageNumber, size,min,max);
		
		page.setPath("BookClientServlet?type=getPageByPrice&min="+min+"&max="+max);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/pages/list/list.jsp").forward(request, response);
		
		
	}
	

}
