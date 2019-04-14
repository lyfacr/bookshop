<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		$(".addA").click(function(){
			var bookId = this.id;
			$.ajax({
				type:"POST",
				url:"CartServlet",
				data:{"type":"addBook","bookId":bookId},
				dataType:"JSON",
				success:function(result){
					//alert("hhh");
					$("#countSpan").text("您的购物车中有 "+ result.totalCount + " 件商品");
					$("#titleDiv").html("您刚刚将<span style='color: red'>"+ result.title +"</span>加入到了购物车中");
				}
			});
			return false;
		});
		
		$("#queryBtn").click(function(){
			var min = $("input[name='min']").val();
			var max = $("input[name='max']").val();
			isNumber(min);
			isNumber(max);
			
			if((min-max)>0){
				alert("请输入正确范围");
			}else{
				window.location="BookClientServlet?type=getPageByPrice&min="+min+"&max="+max;
			}
		});
		function isNumber(val){

		    var regPos = /^\d+(\.\d+)?$/; //非负浮点数
		    var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
		    if(regPos.test(val) || regNeg.test(val)){
		        return true;
		    }else{
		    	val="";
		    }

		}
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user_header.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="min" value="${param.min }"> 元 - <input type="text" name="max" value="${param.max }"> 元 <button id="queryBtn">查询</button>
			</div>
			<c:choose>
				<c:when test="${empty cart.map }">
					<span id="countSpan">您的购物车中还没有商品，快去添加吧！</span>
						<div id="titleDiv">
							<br/>
						</div>
				</c:when>
				<c:otherwise>
					<div style="text-align: center">
						<span id="countSpan">您的购物车中有${sessionScope.cart.totalCount }件商品</span>
						<div id="titleDiv">
							您刚刚将<span style="color: red">${sessionScope.title }</span>加入到了购物车中
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			
			<c:choose>
				<c:when test="${empty page.data }">
					<h3 style="color:red;text-align: center;margin-top: 100px;">您来晚了一步，图书被抢空了.....</h3>
				</c:when>
				<c:otherwise>
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="${pageContext.request.contextPath }${book.imgPath }" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span>
									<span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span>
									<span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span>
									<span class="sp2">${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span>
									<span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span>
									<span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<a id="${book.id }" class="addA" href="CartServlet?type=addBook&bookId=${book.id }">加入购物车</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@ include file="/WEB-INF/include/nav.jsp" %>
	
	<div id="bottom">
		<span>
			网上书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>