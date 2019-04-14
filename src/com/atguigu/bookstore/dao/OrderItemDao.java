package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	int saveOrderItem(OrderItem item);
}
