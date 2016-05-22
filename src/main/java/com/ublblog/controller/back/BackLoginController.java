package com.ublblog.controller.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ublblog.controller.BaseController;
import com.ublblog.model.User;
import com.ublblog.model.UserWithBLOBs;
import com.ublblog.service.UserService;

@Controller
@RequestMapping("/ucenter")
public class BackLoginController extends BaseController{
	
	/**
	 * 用户信息
	 * @return
	 */
	@RequestMapping("/userInfo")
	public ModelAndView initUserInfoPage(HttpServletRequest request) {
		
		ModelAndView response = new ModelAndView("/admin/userInfo");
		//文章标签
		User user = new User();
		user.setId(Integer.parseInt(request.getSession().getAttribute("UserID").toString()));
		try {
			user = userService.getUser(user);
		} catch (Exception e) {
			logger.error("BackUserController.initArticleTagsPage()",e.getMessage());
		}
		response.addObject("user", user);	
		return response;	
	}
	/**
	 * 初始化账户密码管理页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/account")
	public ModelAndView initAccountPage(HttpServletRequest request) {
		
		ModelAndView response = new ModelAndView("/admin/account_edit");
		//文章标签
		User user = new User();
		user.setId(Integer.parseInt(request.getSession().getAttribute("UserID").toString()));
		try {
			user = userService.getUser(user);
		} catch (Exception e) {
			logger.error("BackUserController.initArticleTagsPage()",e.getMessage());
		}
		response.addObject("user", user);	
		return response;	
	}
	
	/**
     * 
     * 更新用户资料
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/update")
    public ModelAndView updateUserInfo(@RequestBody UserWithBLOBs user, HttpServletRequest request) {
        logger.debug("user's id {},name:{},password:{},show_name:{},email:{},description:{},image:{}",user.getId(),user.getName(),user.getPassword(),user.getShowName(),user.getEmail(),user.getDescription(),user.getImage());
    	ModelAndView response = new ModelAndView("/admin/userInfo");
    	
        try {  
        	String redirect = request.getParameter("redirect");
        	// 如果重定向标记不为空,且等于account，则返回页面地址到账户密码修改页面
        	if (redirect != null && redirect.equals("account")) {
        		 response = new ModelAndView("/admin/account_edit");
        	}
        	//返回FAIL
        	response.addObject("message", FAIL);
           
            if (user != null) {
                //如果更新成功,返回SUCCESS
                if (userService.updateUser(user)) { 
                	response.addObject("message", SUCCESS);
                } 
            }        
        } catch (Exception e) {
                logger.error("BackLoginController.updateUserInfo()",e.getMessage());
        }
        response.addObject("user", user);
    	return response; 
    }
	
}
