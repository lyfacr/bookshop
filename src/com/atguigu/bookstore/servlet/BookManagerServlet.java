package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;


public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService service = new BookServiceImpl();
	
	protected void getPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		Page<Book> page = service.getPage(pageNumber, size);
		//给page对象绑定 对应的路径
		page.setPath("BookManagerServlet?type=getPage");
		
		//将page对象设置到request域中
		request.setAttribute("page", page);
		//转发到book_manager.jsp页面显示分页数据
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	
	protected void updateBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		Book book = WebUtils.param2Bean(request, new Book());
		boolean flag = service.updateBook(book);
		
		/*String referer = request.getHeader("referer");
		response.sendRedirect(referer);*/
		String ref = request.getParameter("ref");
		//referer地址必须使用重定向才能跳转
		response.sendRedirect(ref);
		//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getAllBooks");
	}
	
	protected void getBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = service.getBook(bookId);
		//获取修改之前的页面地址
		String referer = request.getHeader("referer");
		//将refere存到request域中
		request.setAttribute("ref", referer);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}
	
	protected void addBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		Book book = WebUtils.param2Bean(request, new Book());
		boolean flag = service.addBook(book);
		response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getPage&pageNumber=1000");
		
	}
	
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		boolean flag = service.deleteBook(bookId);
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
		//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getAllBooks");
	}
	
	protected void getAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = service.getAllBook();
		request.setAttribute("books", books);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	

}
