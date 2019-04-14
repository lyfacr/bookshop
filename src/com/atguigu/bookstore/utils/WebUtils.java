package com.atguigu.bookstore.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;

public class WebUtils {
	
	public static <T>T param2Bean(HttpServletRequest request , T t) {
		Map<String, String[]> map = request.getParameterMap();
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}
