<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑订单</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">编辑订单</span>
			<!-- 服务器解析的路径都使用绝对路径 -->
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
		
		</div>
		
		<div id="main">
			<form action="OrderClientServlet" method="post">
				<input type="hidden" name="type" value="updateState">
				<input type="hidden" name="id" value="${order.id}">
				<input type="hidden" name="ref" value="${ref }"/>
				<table>
					<tr>
						<td>订单编号</td>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>数量</td>
						<td colspan="1">操作</td>
					</tr>		
					<tr>
						
						<td><input name="id" type="text" value="${requestScope.order.id }" readonly="readonly"/></td>
						<td><input name="createTime" type="text" value="${requestScope.order.createTime}" readonly="readonly"/></td>
						<td><input name="totalAmount" type="text" value="${requestScope.order.totalAmount }" readonly="readonly"/></td>
						<td><input name="state" type="text" value="${requestScope.order.state }" /></td>
						<td><input name="totalCount" type="text" value="${requestScope.order.totalCount }" readonly="readonly"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		
		<div id="bottom">
		<span>
			网上书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>