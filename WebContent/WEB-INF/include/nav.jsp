<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- 判断，如果没有分页数据  不显示分页导航栏 -->
	<c:if test="${!empty page.data }">
	<!-- 使用page对象的属性值显示分页导航栏 -->
		<div class="scott">
			<!-- 根据不同的情况生成 页码的begin和end值 -->
			<c:choose>
				<c:when test="${page.totalPage<=5 }">
					<!-- 计算 起始索引和结束索引的值存到域中 -->
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${page.totalPage }"></c:set>
				</c:when>
				<c:otherwise>
					<!-- 根据当前页码判断生成起始和结束索引 -->
					<c:choose>
						<c:when test="${page.pageNumber<=3 }">
							<c:set var="begin" value="1"></c:set>
							<c:set var="end" value="5"></c:set>	
						</c:when>
						<c:otherwise>
							<c:set var="begin" value="${page.pageNumber-2 }"></c:set>
							<c:set var="end" value="${page.pageNumber+2 }"></c:set>
							<!-- 如果pageNumber为 总页码时 ， end值+2超过总页码的范围 -->
							<c:if test="${end>page.totalPage }">
								<!-- 判断end值是否超出范围，如果超出，设置end值=totalPage -->
								<c:set var="end" value="${page.totalPage }"></c:set>
								<!-- 为了保证每次显示5个页码， end值改变后  begin值也需要对应的修改 -->
								<c:set var="begin" value="${end-4 }"></c:set>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		
		
			<c:if test="${page.pageNumber>1 }">
				<a href="${page.path }&pageNumber=${page.pageNumber-1 }"> &lt; </a>
			</c:if>
			<c:forEach begin="${begin }" end="${end }" var="index">
				<c:choose>
					<c:when test="${page.pageNumber==index }">
						<!-- 如果生成的页码 正好是 当前页码，可以高亮显示，并且不让用户点击了 -->
						<span class="current">${index }</span>
					</c:when>
					<c:otherwise>
						<!-- 不是当前页  用户还可以点击访问 -->
						<a href="${page.path }&pageNumber=${index }">${index }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page.pageNumber<page.totalPage }">
				<a href="${page.path }&pageNumber=${page.pageNumber+1 }"> &gt; </a>
			</c:if>
		共${page.totalPage }页，${page.totalCount }条记录 到第<input
			value="${page.pageNumber }" name="pn" id="pn_input" />页 <input
			id="sendBtn" type="button" value="确定">
			
			<!-- 将分页导航栏对应的js代码和分页导航栏写在一起，便于以后提取 -->
			<script type="text/javascript">
				$("#sendBtn").click(function(){
					//获取用户输入的要跳转的页码
					var pageNumber = $("#pn_input").val();
					//请求服务器，访问pageNUmber对应的分页数据:BookManagerServlet?type=getPage&pageNumber=xx
					//只要修改浏览器的地址栏地址，浏览器会自动向修改后的地址发起新的请求
					//地址栏地址是window对象的  location属性
					// 在js代码中如果使用el表达式  必须在 引号内使用
					window.location = "${page.path }&pageNumber="+pageNumber;
					
				});			
			
			</script>
		</div>
	</c:if>