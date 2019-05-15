package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.itheima.constant.Constant;
import com.itheima.conventer.MyConventer;
import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import com.itheima.utils.MD5Utils;
import com.itheima.utils.UUIDUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {

	public String add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		return null;
	}

	public String registUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		return "/jsp/register.jsp";
	}

	public String regist(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		User user = new User();
		
		ConvertUtils.register(new MyConventer(), Date.class);
		
		
		BeanUtils.populate(user, request.getParameterMap());
		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());
		user.setPassword(MD5Utils.md5(user.getPassword()));
		
		UserService service = new UserServiceImpl();
		service.regist(user);
		request.setAttribute("msg", "用户注册成功，请激活。");
		return "/jsp/msg.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String code = request.getParameter("code");
		UserService service = new UserServiceImpl();
		User user = service.active(code);
		if (user==null) {
			request.setAttribute("msg", "请重新注册账号...");
		}else {
			request.setAttribute("msg", "激活成功...");
		}
		
		return "/jsp/msg.jsp";
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/jsp/login.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5Utils.md5(password);
		UserService service = new UserServiceImpl();
		User user = service.login(username,password);
		if (user == null) {
			request.setAttribute("msg", "用户名或密码错误");
			return "/jsp/login.jsp";
			
		}else {
			if (Constant.USER_IS_ACTIVE != user.getState()) {
				request.setAttribute("msg", "用户名未激活");
				return "/jsp/login.jsp";
			}
			
		}
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/");
		return null;
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return null;
	}
}
