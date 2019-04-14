package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;

public class BookDaoImpl extends BaseDao implements BookDao {
	
	@Override
	public int deleteBook(String bookId) {
		String sql = "delete from bs_book where id=?";
		return update(sql,bookId);
	}

	@Override
	public List<Book> findBookList() {
		String sql = "select id,title,author,price,sales,stock,img_path imgPath from bs_book";
		return getBeanList(Book.class,sql);
	}

	@Override
	public int saveBook(Book book) {
		String sql = "insert into bs_book(title,author,price,sales,"
				+ "stock,img_path) values(?,?,?,?,?,?)";
		return update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),
				book.getSales(),book.getStock(),book.getImgPath());
	}

	@Override
	public Book findBookById(String bookId) {
		String sql = "select id,title,author,price,sales,stock,"
				+ "img_path imgPath from bs_book where id=?";
		return getBean(Book.class,sql,bookId);
	}

	@Override
	public int updateBookById(Book book) {
		String sql = "update bs_book set title = ?, author=? , price = ? ,"
				      + " stock=? ,sales=? where id = ?";
		return update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),
				      book.getStock(),book.getSales(),book.getId());
	}

	@Override
	public Page<Book> findPage(Page page) {
		String countSql = "select count(*) from bs_book";
		long obj = (long)getScalar(countSql);
		int totalCount = (int)obj;
		page.setTotalCount(totalCount);
		String sql = "SELECT id , title , author , img_path imgPath, sales , stock , price"
				+ " FROM bs_book LIMIT ? , ?";
		List<Book> List = getBeanList(Book.class,sql,page.getIndex(),page.getSize());
		page.setData(List);
		return page;
	}

	@Override
	public Page<Book> findPageByPrice(Page<Book> page, double minPrice, double maxPrice) {
		String countSql = "select count(*) from bs_book where price between ? and ?";
		long scalar = (long)getScalar(countSql,minPrice,maxPrice);
		int totalCount = (int)scalar;
		page.setTotalCount(totalCount);
		String sql = "SELECT id , title , author , img_path imgPath, sales , stock , price"
				+ " FROM bs_book where price between ? and ? LIMIT ? , ?";
		List<Book> List = getBeanList(Book.class,sql,minPrice,maxPrice,page.getIndex(),page.getSize());
		page.setData(List);
		return page;
	}



}
