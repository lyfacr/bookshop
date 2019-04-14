package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.google.gson.Gson;


public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService service = new BookServiceImpl(); 
	
	protected void updateCount(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String count = request.getParameter("count");
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		cart.updateCountByBookId(bookId, count);
		response.sendRedirect(request.getHeader("referer"));
	}
	
	protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		cart.deleteCartItemByBookId(bookId);
		response.sendRedirect(request.getHeader("referer"));
	}
	
	protected void clearCart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		cart.clearCart();
		response.sendRedirect(request.getHeader("referer"));
	}
       
	protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		HttpSession session = request.getSession();
		Book book = service.getBook(bookId);
		session.setAttribute("title", book.getTitle());
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart==null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		cart.addBook2Cart(book);
		//response.sendRedirect(request.getHeader("referer"));	
		String title = book.getTitle();
		int totalCount = cart.getTotalCount();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("title", title);
		map.put("totalCount", totalCount+"");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.getWriter().write(jsonStr);
	}
	

}
