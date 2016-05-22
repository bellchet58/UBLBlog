package com.ublblog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.ublblog.dao.ArticleTagDao;
import com.ublblog.dao.ArticleTagMapper;
import com.ublblog.dao.RelArticleTagMapper;
import com.ublblog.dto.ArticleTagDto;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleTag;
import com.ublblog.model.ArticleTagExample;
import com.ublblog.model.RelArticleTag;
import com.ublblog.model.RelArticleTagExample;
import com.ublblog.service.ArticleTagService;
@Service
public class ArticleTagServiceImpl implements ArticleTagService{

	@Autowired
	private ArticleTagDao articleTagDao;
	
	@Autowired
	private ArticleTagMapper articleTagMapper;
	
	@Autowired
	private RelArticleTagMapper relArticleTagMapper;
	
	@Override
	public List<ArticleTagDto> getAllTags() throws Exception {
		return articleTagDao.queryAllTags();
	}

	@Override
	public List<ArticleTag> getArticleTags(int  articleId) throws Exception {
		return articleTagDao.queryArticleTags(articleId);
	}

	@Override
	public boolean updateArticleTag(ArticleTag tag) throws Exception {
		int result = articleTagDao.updateTag(tag);
		return result>0;
	}

	@Override
	public boolean deleteTag(ArticleTag tag) throws Exception {
		int result=0;
		if(tag.getId()!=null)
			result = articleTagMapper.deleteByPrimaryKey(tag.getId());
		return result>0;
	}

	@Override
	public boolean deleteArticleTag(RelArticleTag tags) throws Exception {
		int result = relArticleTagMapper.deleteByExample(getRelArticleTagExample(tags));
		return result>0;
	}

	@Override
	public boolean saveArticleTag(ArticleTag tag) throws Exception {
		int result = articleTagMapper.insert(tag);
		return result>0;
	}

	@Override
	@Transactional
	public int saveRelArticleTag(Article article, List<Integer> tags) throws Exception {
		int size=0;
		if(article != null && tags.size() >0)
		{
			for (int i = 0; i < tags.size() ; i++) 
			{
				RelArticleTag rel = new RelArticleTag();
				rel.setArticleId(article.getId());
				rel.setTagId(tags.get(i));
				int count = relArticleTagMapper.insert(rel);
				size += count;
			}
		}
		return size;
	}
	
	private ArticleTagExample getArticleTagExample(ArticleTag tag)
	{
		ArticleTagExample exam = new ArticleTagExample();
		if(tag.getName() != null)
			exam.or().andNameEqualTo(tag.getName());
		if(tag.getDescription() != null)
			exam.or().andNameEqualTo(tag.getDescription());
		return exam;
	}
	
	private RelArticleTagExample getRelArticleTagExample(RelArticleTag relTag)
	{
		RelArticleTagExample exam = new RelArticleTagExample();
		if(relTag.getId() != null)
			exam.or().andIdEqualTo(relTag.getId());
		if(relTag.getArticleId()!=null)
			exam.or().andArticleIdEqualTo(relTag.getArticleId());
		return exam;
	}
}