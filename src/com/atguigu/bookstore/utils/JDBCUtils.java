package com.atguigu.bookstore.utils;
/**
 * 数据库连接工具类
 * @author Administrator
 *
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtils {
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
	//使用线程对象作为键，使用数据库连接作为值， map用来给线程对象绑定对应的数据库连接
	private static Map<Thread , Connection> map = new ConcurrentHashMap<Thread , Connection>();
	//获取连接的方法
	public static Connection getConn() {
		Connection conn = map.get(Thread.currentThread());
		//以后再调用时 获取线程对象绑定的数据库连接
		if(conn==null) {
			//第一次调用此方法时  需要给线程对象绑定
			try {
				conn = source.getConnection();
				//绑定连接
				map.put(Thread.currentThread(), conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	//释放连接的方法
	public static void closeConn(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//移除map中  线程对象绑定的数据
		map.remove(Thread.currentThread());
	}
}
