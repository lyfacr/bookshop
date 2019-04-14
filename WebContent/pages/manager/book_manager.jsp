<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
$(function(){
	$(".delA").click(function(){
		//给用户删除提示
		//this代表被点击的删除超链接  dom对象
		var title = $(this).parents("tr").children("td").first().text();
		var flag = confirm("你真的要删除 <<"+ title +">> 吗？");
		if(!flag){
			//用户取消删除  取消a标签的默认行为
			return false;
		}
		//如果选择确定  则执行a标签的默认行为
		
		
	});
});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${!empty requestScope.page.data }">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					
					<c:forEach items="${requestScope.page.data }" var="book">
						<tr>
							<td>${pageScope.book.title }</td>
							<td>${book.price }</td>
							<td>${book.author }</td>
							<td>${book.sales }</td>
							<td>${book.stock }</td>
							<td><a href="BookManagerServlet?type=getBook&bookId=${book.id}">修改</a></td>
							<td><a class="delA" href="BookManagerServlet?type=deleteBook&bookId=${book.id}">删除</a></td>
						</tr>	
					</c:forEach>
					
					
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><a href="pages/manager/book_add.jsp">添加图书</a></td>
					</tr>	
				</table>
			</c:when>
			<c:otherwise>
				<h3 style="color:red;text-align: center;margin-top: 80px;">生意太好了，图书卖空了，赶快去添加吧！！！<a href="pages/manager/book_edit.jsp">添加图书</a></h3>
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