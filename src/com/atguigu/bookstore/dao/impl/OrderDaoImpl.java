package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao implements OrderDao {

	@Override
	public int saveOrder(Order order) {
		String sql ="INSERT INTO bs_order (id , create_time , state , total_count ,"
				+ " total_amount , user_id) VALUES(? ,?,? ,? ,? ,?)";
		return update(sql,order.getId(),order.getCreateTime(),order.getState(),
				order.getTotalCount(),order.getTotalAmount(),order.getUserId());
	}

	@Override
	public List<Order> listOrder() {
		String sql ="SELECT id,total_amount totalAmount, total_count totalCount,state, create_time createTime, " + 
				" user_id userId FROM bs_order";
		return getBeanList(Order.class,sql);
	}

	@Override
	public List<Order> listOrderByUserId(Integer userId) {
		String sql ="SELECT id,total_amount totalAmount, total_count totalCount,state, create_time createTime, " + 
				" user_id userId FROM bs_order "
				+ "WHERE user_id = ?";
		return getBeanList(Order.class,sql,userId);
	}

	@Override
	public int updateStateById(Order order) {
		String sql = "update bs_order set state = ? where id = ?";
		return update(sql,order.getState(),order.getId());
	}

	@Override
	public Order getOrder(String orderId) {
		String sql = "select id,total_amount totalAmount, total_count totalCount,state, create_time createTime,"
				+ " user_id userId FROM bs_order where id = ?";
		return getBean(Order.class,sql,orderId);
	}

	@Override
	public int deleteOrder(String orderId) {
		String sql = "delete from bs_order where id=?";
		String sql2 = "delete from bs_orderitem where order_id = ?";
		update(sql2,orderId);
		return update(sql,orderId);
	}

	@Override
	public List<OrderItem> listOrderItemById(String orderId) {
		String sql = "select id, title , author , img_path , price , count , amount , order_id  "
				+ " FROM bs_orderitem where order_id = ?";
		
		return getBeanList(OrderItem.class,sql,orderId);
	}

	@Override
	public Page<Order> findPage(Page<Order> page) {
		String countSql = "select count(*) from bs_order";
		long obj = (long)getScalar(countSql);
		int totalCount = (int)obj;
		page.setTotalCount(totalCount);
		String sql = "select id,total_amount totalAmount, total_count totalCount,state, create_time createTime,"
				+ " user_id userId FROM bs_order LIMIT ? , ?";
		List<Order> List = getBeanList(Order.class,sql,page.getIndex(),page.getSize());
		page.setData(List);
		return page;
	}

	@Override
	public Page<Order> findPageByUsrId(Page<Order> page, String userId) {
		String countSql = "select count(*) from bs_order";
		long obj = (long)getScalar(countSql);
		int totalCount = (int)obj;
		page.setTotalCount(totalCount);
		String sql = "select id,total_amount totalAmount, total_count totalCount,state, create_time createTime,"
				+ " user_id userId FROM bs_order where user_id = ? LIMIT ? , ?";
		List<Order> List = getBeanList(Order.class,sql,userId,page.getIndex(),page.getSize());
		page.setData(List);
		return page;
	}

}
