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
		var title = $(this).parents("tr").children("td").eq(1).text();
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
			<span class="wel_word">用户管理系统</span>
			<%@ include file="/WEB-INF/include/manager_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty requestScope.page }">
				<h5>没有查询到员工数据！！！</h5>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>Id</td>
						<td>用户名</td>
						<td>邮箱</td>
						<td colspan="1">操作</td>
					</tr>
	
					<c:forEach items="${requestScope.page.data }" var="user">
						<tr>
							<td><c:out value="${user.id }"></c:out></td>
							<td><c:out value="${user.username }"></c:out></td>
							<td><c:out value="${user.email }"></c:out></td>
							<td><a class="delA" href="UserManagerServlet?type=deleteUser&userId=${user.id}">删除</a></td>
						</tr>
					</c:forEach>
						
			
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>	
				</table>
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