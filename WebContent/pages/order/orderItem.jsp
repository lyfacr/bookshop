<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>

	<div id="main">

		<c:choose>
			<c:when test="${!empty requestScope.orderItems}">
				<table>
					<tr>
						<td>id</td>
						<td>书名</td>
						<td>作者</td>
						<td>价钱</td>
						<td>数量</td>
						<td>总量</td>
					</tr>		
					
					<c:forEach items="${requestScope.orderItems}" var="orderItem">
						<tr>
							<td><input name="id" type="text" value="${orderItem.id }" readonly="readonly"/></td>
							<td><input name="title" type="text" value="${orderItem.title}" readonly="readonly"/></td>
							<td><input name="author" type="text" value="${orderItem.author }" readonly="readonly"/></td>
							<td><input name="price" type="text" value="${orderItem.price }" readonly="readonly"/></td>
							<td><input name="count" type="text" value="${orderItem.count }" readonly="readonly"/></td>
							<td><input name="amount" type="text" value="${orderItem.amount }" readonly="readonly"/></td>
						</tr>	
					</c:forEach>
					
					
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="index.jsp">去购物</a></td>
					</tr>	
				</table>
			</c:when>
			<c:otherwise>
				<h3 style="color:red;text-align: center;margin-top: 80px;">您没有订单，赶快去添加吧！！！<a href="index.jsp">去购物</a></h3>
			</c:otherwise>
		</c:choose>

	</div>

	<div id="bottom">
		<span> 网上书城.Copyright &copy;2018 </span>
	</div>
</body>
</html>