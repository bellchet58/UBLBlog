package com.ublblog.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ublblog.controller.BaseController;
import com.ublblog.model.ExtendPage;
import com.ublblog.model.User;
import com.ublblog.service.UserService;

@Controller
@RequestMapping("/user")
public class LoginController extends BaseController{
	
	
	@RequestMapping("/init-login")
	public ModelAndView initLoginPage()
	{
		List<ExtendPage> pages = extendPageService.getAllPages();
		return new ModelAndView("/login").addObject("pages",pages);
	}

	/**
	 * 管理登录
	 * @param user
	 * @return ModelAndView
	 */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView login(String name, String password, 
			HttpServletRequest request) {
        
    	ModelAndView res = new ModelAndView("/login");
        try {  
        	User user = new User();
        	user.setName(name);
        	user.setPassword(password);
        	User temp = new User();
        	temp.setName(user.getName());
        	user = userService.getUser(user);
        	
        	if (user != null&& temp.getName().equals(user.getName())) {//登录成功
        		request.getSession().setAttribute("UserID", user.getId());
        		request.getSession().setAttribute("UserName", user.getName());
        		logger.debug("将要转向/admin/ucenter");
        		return new ModelAndView("redirect:/admin/ucenter");
        	} else {//登录失败
        		res.addObject("message", INVALID_USER);
        	}
        } catch (Exception e) {
                logger.error("UserController.login();", e.getMessage());
                res.addObject("message", ERROR);
        }
        //返回FAIL
    	return res;
	}
	/**
	 * 管理退出
	 * @return ModelAndView
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("UserID");
		request.getSession().removeAttribute("UserName");
		return new ModelAndView("redirect:/article/index");
	}
	
}
