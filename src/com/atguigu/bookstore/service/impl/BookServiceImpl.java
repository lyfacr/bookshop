package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	BookDao dao = new BookDaoImpl();
	@Override
	public List<Book> getAllBook() {
		return dao.findBookList();
	}
	@Override
	public boolean deleteBook(String bookId) {
		
		return dao.deleteBook(bookId)>0;
	}
	@Override
	public boolean addBook(Book book) {
		return dao.saveBook(book)>0;
	}
	@Override
	public Book getBook(String bookId) {
		return dao.findBookById(bookId);
	}
	@Override
	public boolean updateBook(Book book) {
		return dao.updateBookById(book)>0;
	}
	@Override
	public Page<Book> getPage(String pageNumber, int size) {
		Page<Book> page = new Page<Book>();
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
	public Page<Book> getPageByPrice(String pageNumber, int size, String min, String max) {
		Page<Book> page = new Page<Book>();
		int number = 1;
		double minPrice = 0;
		double maxPrice = 1000;
		try {
			if(pageNumber!=null && pageNumber!="") {
				number = Integer.parseInt(pageNumber);	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(min!=null && min!="") {
				minPrice = Double.parseDouble(min);	
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(max!=null && max!="") {
				maxPrice = Double.parseDouble(max);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		page.setPageNumber(number);
		page.setSize(size);
		return dao.findPageByPrice(page, minPrice, maxPrice);
	}

}
