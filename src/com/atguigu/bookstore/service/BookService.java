package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookService {
	Book getBook(String bookId);
	List<Book> getAllBook();
	boolean deleteBook(String bookId);
	boolean addBook(Book book);
	boolean updateBook(Book book);
	Page<Book> getPage(String pageNumber , int size);
	Page<Book> getPageByPrice(String pageNumber , int size , String min , String max);
}
