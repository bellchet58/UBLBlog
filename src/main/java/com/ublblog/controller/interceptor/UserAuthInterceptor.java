package com.ublblog.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ublblog.model.User;
import com.ublblog.service.UserService;


public class UserAuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private UserService userService;
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger("com.ublblog.controller.interceptor.UserauthInterceptor");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		User user = new User();
		if(request.getSession().getAttribute("UserID")!=null)
		{
			user.setId(Integer.parseInt(request.getSession().getAttribute("UserID").toString()));
			User temp = userService.getUser(user);
			if(temp != null)
				return true;		
		}
		
		response.setCharacterEncoding("utf-8");
        response.sendRedirect(request.getContextPath()+"/user/init-login");
        logger.debug("拦截器正在工作");
		return super.preHandle(request, response, handler);
	}
	
	
	
}
