package com.ublblog.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ublblog.controller.BaseController;
import com.ublblog.model.ArticleCategory;

@Controller
@RequestMapping("/admin/category")
public class BackArticleCategoryController extends BaseController{
	/**
	 * 添加标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addCategory(ArticleCategory category) {
		category.setId(null);
		int originalSortLevel=0;
		category.setSort(originalSortLevel);
		try {
			if (articleCategoryService.addCategory(category)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("BackArticleCategoryController.addCategory()",e.getMessage());
		}
		return FAIL;
	}
	/**
	 * 更新标签
	 * @param tag
	 * @return JSONString
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateCategory(ArticleCategory category) {
		
		try {
			if (articleCategoryService.updateCategory(category)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("BackArticleCateController.updateCategory()",e.getMessage());
		}
		return FAIL;
	}
}
