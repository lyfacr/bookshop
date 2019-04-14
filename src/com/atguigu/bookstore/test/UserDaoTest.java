package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class UserDaoTest {
	private UserDao dao = new UserDaoImpl();
	@Test
	public void testInsert() {
		//注册：
		User user = new User(null, "admin", "123456", "admin@126.com");
		//调用userdao将数据存到数据库
		int i = dao.saveUser(user);
		System.out.println(i);
		
	}
	
	@Test
	public void testQuery() {
		//登录：
		User user = new User(null, "admin", "123456", null);
		//调用dao查询用户
		User loginUser = dao.getUserByUsernameAndPassword(user);
		System.out.println(loginUser);
		
		
	}
	
	

}
