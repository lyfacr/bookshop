package com.atguigu.bookstore.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.SQLException;


public class TransactionFilter extends HttpFilter{

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//获取数据库连接
				Connection conn = JDBCUtils.getConn();
				try {
					//给当前请求开启事务
					conn.setAutoCommit(false);
					//执行后续的多个sql操作：放行请求 让后续代码执行
					chain.doFilter(request, response);
					//正常执行到此处，放行执行没有异常，提交事务
					conn.commit();
				} catch (Exception e) {
					e.printStackTrace();
					//放行的代码执行的有异常，回滚
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					//给用户一个错误页面提示
					response.sendRedirect(request.getContextPath()+"/pages/error/error.jsp");
				}finally {
					//关闭数据库连接
					JDBCUtils.closeConn(conn);
				}
		
	}

}
