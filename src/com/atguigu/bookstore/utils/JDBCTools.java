package com.atguigu.bookstore.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCTools {
	//声明数据库连接池
	private static DataSource source;
	//通过静态代码块初始化数据库连接池
	static {
		Properties info = new Properties();
		//在外部创建数据库连接池需要的配置参数，然后加载到info中
		//web项目必须通过类加载器加载类路径下的配置文件
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			info.load(is);
			source = DruidDataSourceFactory.createDataSource(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//创建ThreadLocal给线程对象绑定数据
	private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
	//获取线程对象对应的数据库连接的方法
	public static Connection getConn() {
		Connection conn = local.get();
		//判断线程对象是否已经绑定连接了，如果没有绑定需要先绑定
		if(conn==null) {
			try {
				conn = source.getConnection();
				//一定将 线程对象和 数据库连接绑定
				local.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	//关闭连接 并释放线程对象绑定连接的方法
	public static void closeConn() {
		Connection conn = local.get();
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//一定要移除local中没有用的数据
		local.remove();
	}
	
	
}
