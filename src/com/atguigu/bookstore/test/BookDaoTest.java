package com.atguigu.bookstore.test;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class BookDaoTest {
	private BookDao dao = new BookDaoImpl();
	
	@Test
	public void testInsert() {
		
		int i = dao.saveBook(new Book(null, "java从入门到放弃", "CLS",80 , 2, 100, "/static/img/default.jpg"));
		dao.saveBook(new Book(null, "php从入门到放弃", "魏运慧", 66, 0, 100, "/static/img/default.jpg"));
		dao.saveBook(new Book(null, "BigData从入门到放弃", "大海", 88, 2, 100, "/static/img/default.jpg"));
		dao.saveBook(new Book(null, "HTML5从入门到乞讨", "晓飞哥", 99, 2, 100, "/static/img/default.jpg"));
		
	}
	@Test
	public void testQueryAll() {
		List<Book> bookList = dao.findBookList();
		System.out.println(bookList);
	}
}
