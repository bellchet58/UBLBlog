package com.ublblog.dao;

import java.util.List;

import com.ublblog.dto.ArticleTagDto;
import com.ublblog.model.ArticleTag;

public interface ArticleTagDao {
	/**
     * 查询所有标签
     * @return List<ArticleTag>
     * @see [类、类#方法、类#成员]
     */
    public List<ArticleTagDto> queryAllTags();
	/**
     * 检索某文章的
     * @param param
     * @return List<ArticleTag>
     */
    public List<ArticleTag> queryArticleTags(int articleId);
    
    public int updateTag(ArticleTag tag);
}
