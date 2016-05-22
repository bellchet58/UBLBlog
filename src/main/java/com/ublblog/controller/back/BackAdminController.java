package com.ublblog.controller.back;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ublblog.controller.BaseController;
import com.ublblog.dto.ArticleDto;
import com.ublblog.dto.ArticleTagDto;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleCategory;
import com.ublblog.model.ArticleTag;
import com.ublblog.model.FriendlyLink;

@Controller
@RequestMapping("/admin")
public class BackAdminController extends BaseController {
	
	@RequestMapping("/ucenter")
	public ModelAndView userCenter(ModelMap model)
	{
		ModelAndView response = new ModelAndView("/admin/index");
		List<ArticleCategory> artCates = null;
        //标签列表
        List<ArticleTagDto> tags = null;
		try {
			tags = articleTagService.getAllTags();
			artCates = articleCategoryService.getAllArtiCategorys();	
		} catch (Exception e) {
			logger.error("AdminController.initAdminPage()",e.getMessage());
		}
		response.addObject("artCates", artCates);
		response.addObject("tags", tags); 
		
		return response;	
	}
	
	/**
	 * 管理所有文章
	 * @return
	 */
	@RequestMapping("/articles")
	public ModelAndView initMageArticles(Integer pageNum, Integer cat) {
        
        ModelAndView response = new ModelAndView("/admin/articles");
        List<ArticleDto> data = null;
        Article article = null;
        int pnum = (pageNum==null)||(pageNum==0)?1:pageNum;

        if (cat != null) {
            article = new Article();
            article.setCategory(cat);
        }
        
        try {
        	//获取文章
        	data = articleService.getArticles(article, pnum);
        	PageInfo<ArticleDto> page = new PageInfo<ArticleDto>(data);
            if (data != null) {                
                response.addObject("articles", data); 
                response.addObject("page", page);
            }
        } catch (Exception e) {
           logger.error("AdminController." +
           		"initMageArticles();", e.getMessage());
        }
        
        return response;		
	}
	
	/**
	 * 初始化管理页面首页
	 * @return ModelAndView
	 */
	@RequestMapping("/frlink")
	public ModelAndView initFriendlyLinkPage() {
		
		ModelAndView response = new ModelAndView("/admin/friendly_link");
        List<FriendlyLink> links = null;
		try {
            links = friendlyLinkService.getFriendlyLinks();
		} catch (Exception e) {
			logger.error("AdminController.initFriendlyLinkPage()",e.getMessage());
		}
		response.addObject("links", links);	
		return response;	
	}
	
	/**
	 * 文章标签管理页面初始化
	 * @return
	 */
	@RequestMapping("/tags")
	public ModelAndView initArticleTagsPage() {
		
		ModelAndView response = new ModelAndView("/admin/article_tags");
		//文章标签
	    List<ArticleTagDto> tags = null;
		try {
			tags = articleTagService.getAllTags();
		} catch (Exception e) {
			logger.error("AdminController.initArticleTagsPage()",e.getMessage());
		}
		response.addObject("tags", tags);	
		return response;	
	}
	
	/**
	 * 文章分类管理页面初始化
	 * @return
	 */
	@RequestMapping("/categorys")
	public ModelAndView initArticleCatesPage() {
		
		ModelAndView response = new ModelAndView("/admin/article_categorys");
		//文章标签
	    List<ArticleCategory> categorys = null;
		try {
			categorys = articleCategoryService.getAllArtiCategorys();
		} catch (Exception e) {
			logger.error("AdminController.initArticleTagsPage()",e.getMessage());
		}
		response.addObject("categorys", categorys);	
		return response;	
	}
	
}
