<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">结算</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId }</h1>
		
	
	</div>
	
	<div id="bottom">
		<span>
			网上书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>