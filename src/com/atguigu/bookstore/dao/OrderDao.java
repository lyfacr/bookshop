package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.Page;

public interface OrderDao {
	int saveOrder(Order order);
	
	List<Order> listOrder();
	
	List<Order> listOrderByUserId(Integer userId);

	int updateStateById(Order order);

	Order getOrder(String orderId);

	int deleteOrder(String orderId);

	List<OrderItem> listOrderItemById(String orderId);

	Page<Order> findPage(Page<Order> page);

	Page<Order> findPageByUsrId(Page<Order> page, String userId);
}
