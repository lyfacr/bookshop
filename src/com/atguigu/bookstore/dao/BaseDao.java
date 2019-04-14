package com.atguigu.bookstore.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 操作数据库的基类
 */
public class BaseDao {
	//初始化操作数据库的工具类
	private QueryRunner runner = new QueryRunner();
	
	//1、增删改
	public int update(String sql , Object...params) {
		Connection conn = JDBCUtils.getConn();
		int i = 0;
		try {
			i = runner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//释放连接
			//JDBCUtils.closeConn(conn);
		}
		return i;
	}
	//2、查询一条记录封装为一个对象的方法
	public <T> T getBean(Class<T> type , String sql, Object...params) {
		Connection conn = JDBCUtils.getConn();
		T t = null;
		try {
			t = runner.query(conn, sql, new BeanHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return t;
	}
	//3、查询多条记录封装为对象集合的方法
	public <T> List<T> getBeanList(Class<T> type , String sql , Object...params){
		Connection conn = JDBCUtils.getConn();
		List<T> list = null;
		try {
			list = runner.query(conn, sql, new BeanListHandler<>(type), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		return list;
	}
	//4、查询第一行第一列数据的方法  : select count(*) from bs_user;
	public Object getScalar(String sql , Object...params) {
		Object obj = null;
		Connection conn = JDBCUtils.getConn();
		//ScalarHandler默认将第一行第一列的数据封装为Object类型的对象返回
		try {
			obj = runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
		
		return obj;
		
	}
	//5、批量增删改的方法
	/**
	 * 批处理：
	 * 	执行的次数，使用二维数组第一维表示次数
	 * 	每一次批处理执行sql语句 ， 第二维携带每次批处理需要的占位符参数
	 */
	public void batchUpdate(String sql , Object[][]params) {
		Connection conn = JDBCUtils.getConn();
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			//JDBCUtils.closeConn(conn);
		}
	}
	
	
	
	
	
	
	
}
