package com.atguigu.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.utils.WebUtils;


public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	UserService service = new UserServiceImpl();
	
	protected void checkUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean b = service.checkUsername(username);
		if(b) {
			//账号可用   响应 "1" 
			response.getWriter().write("1");
		}else {
			//账号不可用 响应 "0"
			response.getWriter().write("0");
		}
		
		
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
       
	protected void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");*/

		User user = WebUtils.param2Bean(request, new User());
		User loginUser = service.login(user);
		if(loginUser == null) {
			String errorMsg = "账号或密码错误...";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("user", loginUser);
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	}


	protected void regist(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");*/
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String serverCode = (String) session.getAttribute("KAPTCHA_SESSION_KEY");
		if(code!=null && code.equals(serverCode)) {
			User user = WebUtils.param2Bean(request, new User());
			boolean flag = service.regist(user);
			if(flag) {
				User login = service.login(user);
				session.setAttribute("user", login);
				response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
			}else {
				String errorMsg = "注册失败,用户已存在";
				request.setAttribute("errorMsg", errorMsg);
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
			
		}else {
			String errorMsg = "验证码错误！";
			request.setAttribute("errorMsg", errorMsg);
			//注册失败：转发到注册页面让用户继续注册
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}
	
}
