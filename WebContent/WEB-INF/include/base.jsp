<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--  用户第一次访问 服务器都会在浏览器输入url地址，提交请求到服务器时，请求报文中会携带  请求地址，我们在代码中可以动态获取到url地址中需要使用的部分拼接base标签  -->    
<%-- 协议： <%=request.getScheme() %>   <br/> 
主机名： <%=request.getServerName() %>   <br/> 
端口号：<%=request.getServerPort() %><br/> 
上下文路径：<%=request.getContextPath() %><br/> --%>
<!-- <base href="http://localhost:8080/BookStore_EG02/"/> -->
<%-- <base href="<%=request.getScheme() %>://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/> --%>
<base href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<!-- 编写js代码 ， 可以引入jquery文件提高js开发效率 -->
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>