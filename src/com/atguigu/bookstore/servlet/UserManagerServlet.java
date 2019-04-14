package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

public class UserManagerServlet extends BaseServlet {
private static final long serialVersionUID = 1L;
	
	private UserService service = new UserServiceImpl();
	
	protected void getPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		Page<User> page = service.getPage(pageNumber, size);
		//给page对象绑定 对应的路径
		page.setPath("UserManagerServlet?type=getPage");
		
		//将page对象设置到request域中
		request.setAttribute("page", page);
		//转发到book_manager.jsp页面显示分页数据
		request.getRequestDispatcher("/pages/manager/user_manager.jsp").forward(request, response);
	}
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String userId = request.getParameter("userId");
		boolean flag = service.deleteUser(userId);
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
		//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getAllBooks");
	}
}
