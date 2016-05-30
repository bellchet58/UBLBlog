package com.ublblog.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ublblog.dao.ArticleDao;
import com.ublblog.dao.ArticleMapper;
import com.ublblog.dao.UserMapper;
import com.ublblog.dto.ArticleDto;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleExample;
import com.ublblog.service.ArticleService;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "/spring/application-config.xml" })
public class ArticleServiceImpTest {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ArticleService articleService;
	

	@Test 
	public void testHotArticle() throws Exception{
		List<ArticleDto> list = articleService.getHotArticles(1);
		assertEquals((Integer)1, list.get(0).getId());
	}
	
	@Test
	public void testUpdateArticle()
	{
		Article article = new Article();
		article.setId(11);
		article.setTitle("asdaa**");
		article.setContent("<p>sdasfafssfasf</p> ");
		article.setCategory(2);
		article.setDeleted(0);
		try {
			boolean success = articleService.updateArticle(article);
			assertTrue(success);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	
	

}
