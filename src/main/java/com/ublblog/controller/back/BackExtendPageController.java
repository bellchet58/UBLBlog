package com.ublblog.controller.back;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ublblog.controller.BaseController;
import com.ublblog.model.ExtendPage;
@Controller
@RequestMapping("/admin/page")
public class BackExtendPageController extends BaseController {

	/**
	 * 浏览列表
	 * @param pageId
	 * @return ModelAndView
	 */
    @RequestMapping("/list")
    public ModelAndView getPage() {
        
        ModelAndView response = new ModelAndView("/admin/extend_page");
        List<ExtendPage> pages = null;
        try {  
        	pages = extendPageService.getAllPages();
        } catch (Exception e) {
            logger.error("ExtendPageController.getPage();", e.getMessage());
        } 
        response.addObject("pages", pages); 
        return response;
    }
    
    /**
     * 初始化编辑
     * @param pageId
     * @return ModelAndView
     */
    @RequestMapping("/init")
    public ModelAndView initEditPage(Integer pageId,String action) {
        
        ModelAndView response = new ModelAndView("/admin/page_edit");
        ExtendPage page = new ExtendPage();
        try {  
        	if (action.equals("update")) {
        		page = extendPageService.getPage(pageId);  
        		action = "/admin/page/update";
        	} else if(action.equals("add")) {
        		action = "/admin/page/add";
        	}
        } catch (Exception e) {
            logger.error("ExtendPageController.initEditPage();", e.getMessage());
        } 
        response.addObject("page", page);    
        response.addObject("action", action);    
        return response;
    }
	
	/**
	 * 更新页面
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/update")
	public ModelAndView updatePage(ExtendPage page) {
		
		ModelAndView response = new ModelAndView("/admin/page_edit");
		response.addObject("action", "/admin/page/update"); 
		response.addObject("page", page);  
		try {
			if (extendPageService.updatePageInfo(page)) {
				return new ModelAndView("redirect:/admin/page/list");
			}
		} catch (Exception e) {
			logger.error("ExtendPageController.updateTag()",e.getMessage());
		}
	  	response.addObject("message", FAIL);
    	return response; 
	}
	
	/**
	 * 删除页面
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/delete")
	public ModelAndView deletePage(int pageId) {
		ModelAndView response = new ModelAndView("redirect:/admin/page/list");
		try {
			if (extendPageService.deletePage(pageId)) {
				return response;			
			}
		} catch (Exception e) {
			logger.error("ExtendPageController.deletePage()",e.getMessage());
		}
     	response.addObject("message", FAIL);
    	return response; 
	}
	
	/**
	 * 添加新页面
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/add")
	public ModelAndView addPage(ExtendPage page) {
		
		ModelAndView response = new ModelAndView("/admin/page_edit");
		response.addObject("action", "/admin/page/add"); 
		response.addObject("page", page);  
		
		try {
			if (extendPageService.addPage(page)) {
				return new ModelAndView("redirect:/admin/page/list");
			}
		} catch (Exception e) {
			logger.error("ExtendPageController.addPage()",e.getMessage());
		}
     	response.addObject("message", FAIL);
    	return response; 
	}
}
