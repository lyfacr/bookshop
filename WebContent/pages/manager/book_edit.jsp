<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
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
			<span class="wel_word">编辑图书</span>
			<!-- 服务器解析的路径都使用绝对路径 -->
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
		
		</div>
		
		<div id="main">
			<form action="BookManagerServlet" method="post">
				<input type="hidden" name="type" value="updateBook">
				<input type="hidden" name="id" value="${book.id}">
				<input type="hidden" name="ref" value="${ref }"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td>图片</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="title" type="text" value="${requestScope.book.title }"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price }"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author }"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales }"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock }"/></td>
						<td><input name="imgPath" type="text" value="${requestScope.book.imgPath }"/></td>
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