package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;

public interface OrderService {
	String createOrder(Cart cart , User user);
	
	List<Order> listOrder();
	
	List<Order> listOrderByUserId(Integer userId);

	boolean updateState(Order book);

	Order getOrder(String orderId);

	boolean deleteOrder(String orderId);

	List<OrderItem> listOrderItemById(String orderId);

	Page<Order> getPage(String pageNumber, int size);

	Page<Order> getPageByUserId(String pageNumber, int size, String userId);
}
