package com.ublblog.controller.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ublblog.constants.Common;
import com.ublblog.controller.BaseController;
import com.ublblog.dto.ArticleCategoryDto;
import com.ublblog.dto.ArticleDto;
import com.ublblog.dto.ArticleTagDto;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleTag;
import com.ublblog.model.ExtendPage;
import com.ublblog.model.FriendlyLink;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController{
	
	
	
	@RequestMapping("/index")
    public ModelAndView getArticles(Integer num, Integer cat) {
        
        ModelAndView response = new ModelAndView("/index");
        //文章实例
        Article article = null;    
        
        List<ArticleDto> data = null;
        
        int pageNum = (num== null|| num==0)? 1:num;
        
        logger.debug("pagenum :{}",pageNum);
        
        //如果分类ID不为空的话，那么按文章分类检索文章，跳转至article_list页面
        if (cat != null) {
        	//改变分页数量
            article = new Article();
            article.setCategory(cat);
            response = new ModelAndView("/article_list");
            //参数带至跳转页面
            response.addObject("catId", cat);
            String action = "/article/index";
            response.addObject("action", action);  
        }
        
        try {
        	data = articleService.getArticles(article,pageNum);
        } catch (Exception e) {
           logger.error("ArticleController." +
           		"getArticles();", e.getMessage());
        }
        //设置page并重新分页   
        response.addObject("articles", data);    
        PageInfo<ArticleDto> pageInfo = new PageInfo<ArticleDto>(data);
        response.addObject("page", pageInfo);        
        //添加内容导航列表
        addContentNavList(response,pageNum);
        return response;
    }
	
	public void addContentNavList(ModelAndView response,Integer pageNum) {
        //热门文章列表,归档列表
        List<ArticleDto> hotArticles = null,archives = null;
        //友情链接
        List<FriendlyLink> links = null;
        //分类列表
        List<ArticleTagDto> tags = null;
        //文章分类列表
        List<ArticleCategoryDto> categorys = null;
        
        List<ExtendPage> pages = null;
        
        List<ArticleDto> articles = null;
        
        int pageSize = 10;
        
        pageNum = (pageNum==null||pageNum==0)?1:pageNum;
        //其他页面
//        List<ExtendPage> pages = null;
        int articleCount = 0;
        try {
        	links = friendlyLinkService.getFriendlyLinks();
        	tags = articleTagService.getAllTags();
        	hotArticles = articleService.getHotArticles(pageNum);
        	archives = articleService.getArchiveByTime();
        	articles = articleService.getArticles(null, pageNum);
        	articleCount = (int) ((Page<ArticleDto>)articles).getTotal();
        	categorys = articleCategoryService.getArtCatesAndCount();
        	pages = extendPageService.getAllPages();

        } catch (Exception e) {
           logger.error("ArticleController." +
           		"getArticles();", e.getMessage());
        }
        response.addObject("links", links);  
        response.addObject("countOfAllArticles", articleCount);  
        response.addObject("hotArticles", hotArticles);  
        response.addObject("archives", archives);  
        response.addObject("categorys", categorys); 
        response.addObject("pages",pages);
        response.addObject("currentPage", ((Page<ArticleDto>)articles).getPageNum()); 
        response.addObject("tags", tags); 
    }
	
	/**
     * 
     * 获取所有的文章列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/list")
    public ModelAndView articleList(Integer num, Integer tagId) {
        
        ModelAndView response = new ModelAndView("/article_list");
        List<ArticleDto> data = null;
        int pageNum = num==null?1:num;
        
        try {
        	if (tagId != null) {
        		data = articleService.getArticlesByTagId(tagId,pageNum);
        	} else {        		
        		data = articleService.getArticles(null, pageNum);
        	}
        } catch (Exception e) {
           logger.error("ArticleController." +
           		"articleList();", e.getMessage());
        }
        PageInfo<ArticleDto> pageInfo = new PageInfo<ArticleDto>(data);
        
        response.addObject("articles", data);
        response.addObject("page", pageInfo);
        response.addObject("tagId", tagId);
        String action = "/article/list";
        response.addObject("action", action);  
        //添加内容导航列表
        addContentNavList(response,pageNum);
        return response;
    }
    
    
    
    /**
     * 检索当前月份的文章列表
     * @param time
     * @return
     */
    @RequestMapping("/search-archives")
    public ModelAndView searchByMouth(String time) {
        
        ModelAndView response = new ModelAndView("/article_list");
        List<ArticleDto> data = null;
        int pageNum = 1;
        try {
        	//检索当月文章列表
        	data = articleService.getArticlesByTime(time, Common.BY_MOUTH);
        } catch (Exception e) {
           logger.error("ArticleController." +
           		"searchByTime();", e.getMessage());
        }
       
        response.addObject("articles", data);
        //添加内容导航列表
        addContentNavList(response,pageNum);
        return response;
    }
    
    /**
     * 
     * 获取具体的文章
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/read")
    public ModelAndView getArticle(int id, String flag) {
        
        ModelAndView response = new ModelAndView("/article_detail");
        ArticleDto article = null;
        Article param = new Article();
        //其他页面
        List<ExtendPage> pages = null;
        try {  
        	pages = extendPageService.getAllPages();
        	param.setId(id);
        	article = articleService.getArticle(param);           
            //更新浏览次数
        	if (article != null) {
        		param.setReadCount(article.getReadCount()+1);
        		articleService.updateArticle(param);      		
        	}

        } catch (Exception e) {
            logger.error("ArticleController.getArticle();", e.getMessage());
        } 
        if (article != null && pages != null) {        	
        	response.addObject("article", article);    
        	response.addObject("pages", pages); 
        }
        response.addObject("flag", flag); 
        //添加内容导航列表
        addContentNavList(response,null);

        return response;
    }
}
