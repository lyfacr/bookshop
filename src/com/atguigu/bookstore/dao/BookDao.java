package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookDao {
	int deleteBook(String bookId);
	List<Book> findBookList();
	int saveBook(Book book);
	Book findBookById(String bookId);
	int updateBookById(Book book);
	Page<Book> findPage(Page page);
	Page<Book> findPageByPrice(Page<Book> page, double minPrice , double maxPrice);
}
