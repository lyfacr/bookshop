<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<!-- 引入base页面：包含 样式文件、jquery文件 以及 base标签的引入 
	引入之后页面中的相对路径不在参考文件位置，和base标签进行拼接
-->
<%@ include file="/WEB-INF/include/base.jsp" %>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}
		
	</style>
	<script type="text/javascript">
		/* 提交请求前验证 注册参数是否满足一定的格式 */
		$(function(){
			$("input[name='username']").change(function(){
				//获取表单项内用户输入的账号  通过ajax 提交给服务器检查用户名是否可用，然后根据响应结果给用户提示
				var username = this.value;
				$.ajax({
					type:"POST",
					data:{"type":"checkUsername" , "username": username},// UserServlet?type=xxx&username=xxx
					url: "UserServlet",
					success:function(a){
						//alert(a);
						if(a=="0"){
							//不可用
							$(".errorMsg").text("用户名已被占用！");
							//设置 提交按钮禁用
							$("#sub_btn").attr("disabled" , true);
							$("#sub_btn").css("background-color" , "gray");
						}else if(a=="1"){
							//可用
							$(".errorMsg").text("恭喜你，用户名可用！");
							//设置提交按钮可用
							$("#sub_btn").attr("disabled" , false);
							$("#sub_btn").css("background-color" , "green");
						}
					}
				});
			});
			
			var i = 0;
			$("#codeImg").click(function(){
				this.src="code.jpg?t="+(i++);
			});
			
			$("#sub_btn").click(function(){
				//提交按钮被点击时，先获取用户输入的注册参数
				var username = $("input[name='username']").val();
				var password = $("input[name='password']").val();
				var repwd = $("input[name='repwd']").val();
				var email = $("input[name='email']").val();
				//使用正则表达式验证字符串的规则
				//需要为每一个单独的验证的字符串规则创建一个正则对象
				var unameReg = /^[a-z0-9_-]{3,16}$/;
				//正则对象提供了 boolean reg.test(需要验证的字符串)，如果验证字符串符合要求返回true
				var flag = unameReg.test(username);
				if(!flag){
					//账号不符合要求，给用户错误提示，并取消提交数据
					alert("请输入包含a-z,0-9,_或-组成的3~16位的用户名!");
					return false;
				}
				//创建验证密码的正则对象
				var pwdReg = /^[a-z0-9_-]{6,18}$/;
				if(!pwdReg.test(password)){
					alert("请输入合理的密码!");
					return false;
				}
				//两次密码需要一样
				if(password!=repwd){
					alert("两次密码不一样!");
					return false;
				}
				//创建邮箱验证的正则对象
				var emailReg = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				if(!emailReg.test(email)){
					alert("请输入正确的邮箱地址!");
					return false;
				}
				
			});
		});	
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${errorMsg }</span>
							</div>
							<div class="form">
								<form action="UserServlet" method="post">
									<input type="hidden" name="type" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" 
									value="${param.username }" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" 
									value="${param.email }" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" name="code"/>
									<img id="codeImg" alt="" src="code.jpg" style="float: right; margin-right: 40px;width: 90px;height: 40px;">									
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
		<span>
			网上书城.Copyright &copy;2018
		</span>
	</div>
</body>
</html>