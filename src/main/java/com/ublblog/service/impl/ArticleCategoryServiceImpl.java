package com.ublblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ublblog.dao.ArticleCategoryDao;
import com.ublblog.dao.ArticleCategoryMapper;
import com.ublblog.dto.ArticleCategoryDto;
import com.ublblog.model.ArticleCategory;
import com.ublblog.model.ArticleCategoryExample;
import com.ublblog.service.ArticleCategoryService;
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

	@Autowired
	private ArticleCategoryMapper articleCatMapper;
	
	@Autowired
	private ArticleCategoryDao articleCatDtoMapper;
	
	@Override
	public List<ArticleCategory> getAllArtiCategorys() {
		ArticleCategoryExample exam = new ArticleCategoryExample();
		exam.setOrderByClause("sort desc");
		return articleCatMapper.selectByExample(exam);
	}

	@Override
//	根据sort字段逆序排列
	public List<ArticleCategoryDto> getArtCatesAndCount() {
		List<ArticleCategoryDto> list = articleCatDtoMapper.selectArtCatesAndCount();
		return list;
	}

	@Override
	public boolean addCategory(ArticleCategory category) {
		int result  = articleCatMapper.insert(category);
		return result>0;
	}

	@Override
	public boolean deleteCategory(ArticleCategory category) {
		int result = articleCatMapper.deleteByPrimaryKey(category.getId());
		return result>0;
	}

	@Override
	public boolean updateCategory(ArticleCategory category) {
		int result = articleCatDtoMapper.updateCategory(category);
		return result>0;
	}
	private ArticleCategoryExample getCategoryExam(ArticleCategory cat)
	{
		ArticleCategoryExample exam = new ArticleCategoryExample();
		if(cat.getName() != null)
			exam.or().andNameEqualTo(cat.getName());
		if(cat.getId() != null)
			exam.or().andIdEqualTo(cat.getId());
		return exam;
	}

}
