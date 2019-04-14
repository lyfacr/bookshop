<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".countInput").change(function(){
			var bookId = this.name;
			var count = this.value;
			window.location = "CartServlet?type=updateCount&bookId="+bookId+"&count="+count;
		});
		$(".delA").click(function(){
			var title = $(this).parents("tr").children("td").first().text();
			//当删除链接被点击时给用户友好提示
			if(!confirm("你真的要删除<<"+ title +">>吗？")){
				//用户取消删除，只要取消a标签的默认行为即可
				return false;
			}
			//如果用户选择删除，超链接默认行为提交删除请求给服务器
		});
		$(".delC").click(function(){
			if(!confirm("你真的要清空购物车吗？")){
				//用户取消删除，只要取消a标签的默认行为即可
				return false;
			}
		});
	});
</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty cart.map }">
				<h2>购物车空空如也，快去添加吧！！ </h2>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>		
					
					<c:forEach items="${cart.cartItemList }" var="cartItem">	
						<tr>
							<td>${cartItem.book.title }</td>
							<td><input name="${cartItem.book.id }" type="text" class="countInput" value="${cartItem.count }" style="width: 40px;text-align: center;"/></td>
							<td>${cartItem.book.price }</td>
							<td>${cartItem.amount }</td>
							<td><a class="delA" href="CartServlet?type=deleteCartItem&bookId=${cartItem.book.id }">删除</a></td>
							
						</tr>		
					</c:forEach>
				</table>
				
			</c:otherwise>
		</c:choose>

		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${!empty cart.totalCount ? cart.totalCount : 0 }</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${!empty cart.totalAmount ? cart.totalAmount : 0 }</span>元</span>

			<c:if test="${!empty cart.map }">
				<span class="cart_span"><a class="delC" href="CartServlet?type=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="OrderClientServlet?type=checkout">去结账</a></span>
			</c:if>
			
		</div>
	</div>
	<div id="bottom">
		<span>
			网上书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>