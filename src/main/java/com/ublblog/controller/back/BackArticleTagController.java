package com.ublblog.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ublblog.controller.BaseController;
import com.ublblog.model.ArticleTag;

@Controller
@RequestMapping("/admin/tag")
public class BackArticleTagController extends BaseController{
	
	/**
	 * 更新标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateTag(ArticleTag tag) {
		try {
			if (articleTagService.updateArticleTag(tag)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("BackArticleTagController.updateTag()",e.getMessage());
		}
		return FAIL;
	}
	
	/**
	 * 删除标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteTag(ArticleTag tag) {
		try {
			if (articleTagService.deleteTag(tag)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("ArticleTagController.deleteTag()",e.getMessage());
		}
		return FAIL;
	}
	
	/**
	 * 添加标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addTag(ArticleTag tag) {
		tag.setId(null);
		try {
			if (articleTagService.saveArticleTag(tag)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("ArticleTagController.addTag()",e.getMessage());
		}
		return FAIL;
	}
	
}
