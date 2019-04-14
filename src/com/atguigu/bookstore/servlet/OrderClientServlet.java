package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;

/**
 * 处理用户订单相关请求的Servlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService service = new OrderServiceImpl();
	//处理用户的结账请求
	protected void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、判断用户是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			String errorMsg = "结账操作必须登录...";
			request.setAttribute("errorMsg", errorMsg);//request域共享数据必须使用转发
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//已登录
			//2、准备业务层需要的数据
			Cart cart  = (Cart) session.getAttribute("cart");
			//3、调用业务层处理业务  并接受返回的订单id
			String orderId = service.createOrder(cart, user);
			//4、给用户响应
			//将订单id共享到session域中
			session.setAttribute("orderId", orderId);
			//重定向到 /pages/cart/checkout.jsp显示订单id： 特点  一次请求 地址栏地址不变，打开的页面变了 ， 如果刷新浏览器地址栏会向servlet再次发送结账请求
			response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
		}
	}
	
	protected void getPage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int size = 4;
		Page<Order> page = service.getPage(pageNumber, size);
		//给page对象绑定 对应的路径
		page.setPath("OrderClientServlet?type=getPage");
		
		//将page对象设置到request域中
		request.setAttribute("page", page);
		//转发到book_manager.jsp页面显示分页数据
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}
	
	protected void getPageByUserId(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		String userId = request.getParameter("userId");
		int size = 4;
		Page<Order> page = service.getPageByUserId(pageNumber, size,userId);
		//给page对象绑定 对应的路径
		page.setPath("OrderClientServlet?type=getPage");
		
		//将page对象设置到request域中
		request.setAttribute("page", page);
		//转发到book_manager.jsp页面显示分页数据
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	}
	
	protected void listOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1、判断用户是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		String type = (String)request.getParameter("orderManager");
	
		
		if("managerOrder".equals(type)){
			
			//已登录
			//2、准备业务层需要的数据
			//3、调用业务层处理业务  
			List<Order> orders = service.listOrder();
			
			//4、给用户响应
			//将订单订单共享到request域中
			request.setAttribute("orders", orders);
			//重定向到 /pages/order/order.jsp显示订单： 特点  一次请求 地址栏地址不变，打开的页面变了 ， 如果刷新浏览器地址栏会向servlet再次发送结账请求
			request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
		}else if(user==null) {
			String errorMsg = "查看订单必须登录...";
			request.setAttribute("errorMsg", errorMsg);//request域共享数据必须使用转发
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else if("userOrder".equals(type)){
			//已登录
			//2、准备业务层需要的数据
			//3、调用业务层处理业务  
			List<Order> orders = service.listOrderByUserId(user.getId());
			
			//4、给用户响应
			//将订单订单共享到request域中
			request.setAttribute("orders", orders);
			//重定向到 /pages/order/order.jsp显示订单： 特点  一次请求 地址栏地址不变，打开的页面变了 ， 如果刷新浏览器地址栏会向servlet再次发送结账请求
			request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
		}
	}
	
	protected void updateState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Order order = WebUtils.param2Bean(request, new Order());
		boolean flag = service.updateState(order);
		
		/*String referer = request.getHeader("referer");
		response.sendRedirect(referer);*/
		String ref = request.getParameter("ref");
		//referer地址必须使用重定向才能跳转
		response.sendRedirect(ref);
		//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getAllBooks");
	}
	
	protected void getOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		Order order = service.getOrder(orderId);
		
		System.out.println(order);
		//获取修改之前的页面地址
		String referer = request.getHeader("referer");
		//将refere存到request域中
		request.setAttribute("ref", referer);
		request.setAttribute("order", order);
		request.getRequestDispatcher("/pages/manager/order_edit.jsp").forward(request, response);
	}
	
	protected void deleteOrder(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		boolean flag = service.deleteOrder(orderId);
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
		//response.sendRedirect(request.getContextPath()+"/BookManagerServlet?type=getAllBooks");
	}
	
	protected void getOrderItem(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		//1、判断用户是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null) {
			String errorMsg = "查看订单详情必须登录...";
			request.setAttribute("errorMsg", errorMsg);//request域共享数据必须使用转发
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//已登录
			//2、准备业务层需要的数据
			//3、调用业务层处理业务  
			List<OrderItem> orderItems = service.listOrderItemById(orderId);
			
			//4、给用户响应
			//将订单订单共享到request域中
			request.setAttribute("orderItems", orderItems);
			//重定向到 /pages/order/order.jsp显示订单： 特点  一次请求 地址栏地址不变，打开的页面变了 ， 如果刷新浏览器地址栏会向servlet再次发送结账请求
			request.getRequestDispatcher("/pages/order/orderItem.jsp").forward(request, response);
		}
	}
	
}
