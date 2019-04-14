package com.atguigu.bookstore.service;


import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;

public interface UserService {
	boolean checkUsername(String username);
	User login(User user);
	boolean regist(User user);
	Page<User> getPage(String pageNumber, int size);
	boolean deleteUser(String userId);
}
