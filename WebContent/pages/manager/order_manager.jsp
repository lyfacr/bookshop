<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${!empty requestScope.page}">
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>数量</td>
						<td colspan="2">操作</td>
					</tr>		
					
					<c:forEach items="${requestScope.page.data}" var="order">
						<tr>
							<td>${pageScope.order.id }</td>
							<td>${pageScope.order.createTime }</td>
							<td>${pageScope.order.totalAmount }</td>
							<td>${pageScope.order.state }</td>
							<td>${pageScope.order.totalCount }</td>	
							<td><a href="OrderClientServlet?type=getOrder&orderId=${order.id }">修改状态</a></td>						
							<td><a class="delA" href="OrderClientServlet?type=deleteOrder&orderId=${order.id}">删除</a></td>
						
						</tr>	
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<h3 style="color:red;text-align: center;margin-top: 80px;">还没有订单！！！<a href="index.jsp">去购物</a></h3>
			</c:otherwise>
		</c:choose>
					
	</div>
	<%@ include file="/WEB-INF/include/nav.jsp" %>
	<div id="bottom">
		<span>
			网上书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>