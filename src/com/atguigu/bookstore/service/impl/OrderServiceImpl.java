package com.atguigu.bookstore.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService{
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(Cart cart, User user) {
		//处理业务
		//1、将购物车转为订单，并将订单和用户绑定
		//1.1 创建订单id：  时间戳+用户id
		String orderId = System.currentTimeMillis()+""+user.getId();
		//1.2 给订单设置默认状态： 0代表未发货
		int state = 0;
		//1.3 当前时间就是订单创建的时间
		Date createTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowStr = sdf.format(createTime);
		Order order = new Order(orderId, cart.getTotalAmount(), cart.getTotalCount(), state, nowStr, user.getId());
		//将订单保存到数据库中
		orderDao.saveOrder(order);
		//2、将购物车的购物项集合 转为一个个的订单项 并和订单绑定
		List<CartItem> cartItemList = cart.getCartItemList();
		for (CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			OrderItem orderItem = new OrderItem(null, book.getTitle(), book.getAuthor(), book.getImgPath(), book.getPrice(),
					cartItem.getCount(), cartItem.getAmount(), orderId);
			//将每个订单项都保存到订单项表中
			orderItemDao.saveOrderItem(orderItem);
			//3、每保存一个订单项  都需要修改对应的图书的销量库存
			//每次遍历时  修改 购物项对应的图书的销量库存
			book.setSales(book.getSales()+ cartItem.getCount());//图书之前的销量+订单项的数量
			book.setStock(book.getStock() - cartItem.getCount());//之前的库存- 订单项的数量
			bookDao.updateBookById(book);
		}	
		//4、购物车数据结账完成 需要清空
		cart.clearCart();
		//5、处理后 返回订单id
		return orderId;
	}
	@Override
	public List<Order> listOrder() {
		List<Order> listOrder = orderDao.listOrder();
//		return null != listOrder ? listOrder : new ArrayList<Order>();
		return listOrder;
	}
	@Override
	public List<Order> listOrderByUserId(Integer userId) {
		List<Order> orders = orderDao.listOrderByUserId(userId);
//		return null != orders ? orders : new ArrayList<Order>();
		return orders;
	}
	@Override
	public boolean updateState(Order order) {

		Integer updateStateById = orderDao.updateStateById(order);
		
		return null != updateStateById && updateStateById>0? true:false;
	}
	@Override
	public Order getOrder(String orderId) {
		return orderDao.getOrder(orderId);
	}
	@Override
	public boolean deleteOrder(String orderId) {
		Integer i = orderDao.deleteOrder(orderId);
		return null != i && i>0 ? true:false;
	}
	@Override
	public List<OrderItem> listOrderItemById(String orderId) {

		
		return orderDao.listOrderItemById(orderId);
	}
	@Override
	public Page<Order> getPage(String pageNumber, int size) {
		Page<Order> page = new Page<Order>();
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
		return orderDao.findPage(page);
	}
	@Override
	public Page<Order> getPageByUserId(String pageNumber, int size, String userId) {
		Page<Order> page = new Page<Order>();
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
		return orderDao.findPageByUsrId(page,userId);
	}

}
