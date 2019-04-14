package com.atguigu.bookstore.service.impl;



import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao dao = new UserDaoImpl();
	
	@Override
	public boolean checkUsername(String username) {
		User user = dao.getUserByUsername(username);
		return user==null;//如果查询的用户对象为null，返回true，代表用户名可用
	}

	@Override
	public User login(User user) {
		return dao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		try {
			dao.saveUser(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<User> getPage(String pageNumber, int size) {
		Page<User> page = new Page<User>();
		int number = 1;
		try {
			if(pageNumber!=null) {
				number = Integer.parseInt(pageNumber);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setSize(size);
		page.setPageNumber(number);
		return dao.findPage(page);
	}

	@Override
	public boolean deleteUser(String userId) {
		Integer i = dao.deleteUser(userId);
		return null != i && i>0 ? true:false;
	}
	

}
