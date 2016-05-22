package com.ublblog.dao;

import java.util.List;

import com.ublblog.dto.ArticleCategoryDto;
import com.ublblog.model.ArticleCategory;

public interface ArticleCategoryDao {
	
	public List<ArticleCategoryDto> selectArtCatesAndCount();
	
	public int updateCategory(ArticleCategory cat);
	
}
