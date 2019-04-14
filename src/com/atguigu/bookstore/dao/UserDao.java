package com.atguigu.bookstore.dao;


import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;

public interface UserDao {
	User getUserByUsername(String username);
	User getUserByUsernameAndPassword(User user);
	int saveUser(User user);
	Page<User> findPage(Page<User> page);
	int deleteUser(String userId);
}
