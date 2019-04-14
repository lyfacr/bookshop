package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	private Map<String,CartItem> map = new LinkedHashMap<String , CartItem>();
	private int totalCount;
	private double totalAmount;
	public Map<String, CartItem> getMap() {
		return map;
	}
	public int getTotalCount() {
		totalCount = 0;
		for(CartItem cartItem:getCartItemList()) {
			totalCount += cartItem.getCount();
		}
		return totalCount;
	}
	public double getTotalAmount() {
		totalAmount = 0;
		BigDecimal bd1 = new BigDecimal(totalAmount+"");
		for(CartItem cartItem:getCartItemList()) {
			BigDecimal bd2 = new BigDecimal(cartItem.getAmount()+"");
			bd1 = bd1.add(bd2);
			
			//totalAmount += cartItem.getAmount();
		}
		//将累加后的bd1转为  double类型的结果
		totalAmount = bd1.doubleValue();
		return totalAmount;
	}
	@Override
	public String toString() {
		return "Crat [map=" + map + ", totalCount=" + totalCount + ", totalAmount=" + totalAmount + "]";
	}
	public List<CartItem> getCartItemList(){
		Collection<CartItem> values = map.values();
		return new ArrayList<CartItem>(values);
	}
	
	public void addBook2Cart(Book book) {
		CartItem cartItem = map.get(book.getId()+"");
		if(cartItem==null) {
			cartItem = new CartItem(book,1);
			map.put(book.getId()+"", cartItem);
		}else {
			cartItem.setCount(cartItem.getCount()+1);
		}
	}
	//3、删除指定购物项的方法
	public void deleteCartItemByBookId(String bookId) {
		map.remove(bookId);//根据key移除map中对应的购物项即可
	}
	//4、清空购物车的功能
	public void clearCart() {
		map.clear();//清空map中所有的数据即可
	}
	//5、 修改指定购物项的数量
	public void updateCountByBookId(String bookId , String count) {
		//获取id对应的购物项
		CartItem cartItem = map.get(bookId);
		//数据类型转换
		int cou = cartItem.getCount();//如果用户传入的数量有问题，希望给用户显示之前购买的购物项的数量
		try {
			cou = Integer.parseInt(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将新数量设置给cartItem
		cartItem.setCount(cou);
	}

}
