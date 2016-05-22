package com.ublblog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ublblog.dao.ArticleDao;
import com.ublblog.dao.ArticleMapper;
import com.ublblog.dao.ArticleTagDao;
import com.ublblog.dao.ArticleTagMapper;
import com.ublblog.dto.ArticleDto;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleExample;
import com.ublblog.model.ArticleTag;
import com.ublblog.service.ArticleService;
import com.ublblog.utils.HTMLUtils;
@Service
public class ArticleServiceImpl implements ArticleService{

	private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class.getName());
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ArticleTagMapper articleTagMapper;
	
	@Autowired
	private ArticleTagDao articleTagDao;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private ArticleDao articleDao; 
	
	@Override
	public Page<ArticleDto> getArticles(Article article,int pageNum) throws Exception {
		int pageSize=15;
		Map<String,Object> param = new HashMap<String,Object>();
		
		if (article != null) {
            param.put("id", article.getId());
            param.put("title", article.getTitle());
            param.put("content", article.getContent());
            param.put("category", article.getCategory());
            param.put("createTime", article.getCreateTime());            
        }
		PageHelper.startPage(pageNum, pageSize);
		List<ArticleDto> list = articleDao.selectArticles(param);
		logger.debug("the size of the result of select articles is {}",list.size());
		//获取文章标签
		if(list != null)
		{
			for(ArticleDto art:list)
			{
				// 此处对页面输出作字符限制
				String content = HTMLUtils.html2Text(art.getContent());       			
    			content = content.length() > 500 ? content.substring(0, 500) : content;   					
    			art.setContent(content + " ..." );
    			List<ArticleTag> tagList = articleTagDao.queryArticleTags(art.getId());
    			if(tagList != null)
    			{
    				art.setTags(tagList);
    			}
			}
		}
		
		return (Page<ArticleDto>)list;
	}


	@Override
	public List<ArticleDto> searchArticle(String key) throws Exception {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", key);
		param.put("name", "%"+key+"%");
		return articleDao.searchArticle(param);
	}

	@Override
	public List<ArticleDto> getHotArticles(int pageNum) throws Exception {
		int pageSize = 15;
		PageHelper.startPage(pageNum,pageSize);
		return articleDao.selectHotArticles();
	}

	@Override
	public List<ArticleDto> getArchiveByTime() throws Exception {
		return articleDao.selectArchiveByTime();
	}

	@Override
	public Page<ArticleDto> getArticlesByTagId(int tagId,int pageNum) throws Exception {	
		int size = 15;
		PageHelper.startPage(pageNum,size);
		List<ArticleDto> list = articleDao.selectArticlesByTagId(tagId);
		return (Page<ArticleDto>)list;
	}

	@Override
	public List<ArticleDto> getArticlesByTime(String dateTime, String timeType) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("time", dateTime);
		param.put("type", timeType);
		return articleDao.selectArticlesByTime(param);
	}

	@Override
	public ArticleDto getArticle(Article article) throws Exception {
		ArticleDto atc = articleDao.selectArticle(article);
		logger.debug("the select article's name is {},{}",atc.getId(),atc.getTitle());
		List<ArticleTag> list = articleTagDao.queryArticleTags(atc.getId());
		if(list != null)
		{
			atc.setTags(list);
		}
		return atc;
	}

	@Override
	public boolean addArticle(Article article) throws Exception {
		if(article.getCreateTime() == null)
		{
			article.setCreateTime(new Date());
		}
		article.setDeleted(0);
		article.setReadCount(0);
		int result = articleMapper.insert(article);
		return result>0;
	}

	@Override
	public boolean updateArticle(Article article) throws Exception {
		int result = articleDao.updateArticle(article);
		return result>0;
	}
	
	private ArticleExample getArticleExample(Article article)
	{
		ArticleExample exam = new ArticleExample();
		if(article.getId() != null)
			exam.or().andIdEqualTo(article.getId());
		if(article.getTitle() != null)
			exam.or().andTitleEqualTo(article.getTitle());
		if(article.getCategory() != null)
			exam.or().andCategoryEqualTo(article.getCategory());
		return exam;
	}
	
}
