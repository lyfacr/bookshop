package com.atguigu.bookstore.dao.impl;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao{

	@Override
	public int saveOrderItem(OrderItem item) {
		String sql = "INSERT INTO bs_orderitem (title , author , img_path , price , count , amount , "
				+ "order_id) VALUE(?,?,?,?,?,?,?)";
		return update(sql, item.getTitle() , item.getAuthor() , item.getImgPath() , item.getPrice() 
				, item.getCount(), item.getAmount() , item.getOrderId());
	}

}
