package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Book book;
	private int count;
	private double amount;
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		BigDecimal bd1 = new BigDecimal(book.getPrice()+"");
		BigDecimal bd2 = new BigDecimal(count+"");
		BigDecimal multiply = bd1.multiply(bd2);
		amount = multiply.doubleValue();
		return amount;
	}
	/*public void setAmount(double amount) {
		this.amount = amount;
	}*/
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + ", amount=" + amount + "]";
	}
}
