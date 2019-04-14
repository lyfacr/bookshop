package com.atguigu.bookstore.dao.impl;



import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.UserDao;

public class UserDaoImpl extends BaseDao implements UserDao{
	
	@Override
	public User getUserByUsername(String username) {
		//开发中不推荐直接使用*代表所有列，效率低
		String sql = "SELECT id , username , password ,email FROM bs_user"
				+ " WHERE username = ? ";
		return getBean(User.class, sql, username);
	}

	@Override
	public User getUserByUsernameAndPassword(User user) {
		String sql = "select id,username,password,email from bs_user "+
				     " where username = ? and password = ?";
		return getBean(User.class,sql,user.getUsername(),user.getPassword());
	}

	@Override
	public int saveUser(User user) {
		String sql = "insert into bs_user(username,password,email) values(?,?,?)";
		return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
	}

	@Override
	public Page<User> findPage(Page<User> page) {
		String countSql = "select count(*) from bs_user";
		long obj = (long)getScalar(countSql);
		int totalCount = (int)obj;
		page.setTotalCount(totalCount);
		String sql = "SELECT id , username,password,email "
				+ " FROM bs_user LIMIT ? , ?";
		List<User> List = getBeanList(User.class,sql,page.getIndex(),page.getSize());
		page.setData(List);
		return page;
	}

	@Override
	public int deleteUser(String userId) {
		String sql = "delete from bs_user where id=?";
		return update(sql,userId);
	}
	

}
