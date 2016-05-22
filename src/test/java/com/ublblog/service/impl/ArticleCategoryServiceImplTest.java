package com.ublblog.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ublblog.dto.ArticleCategoryDto;
import com.ublblog.model.ArticleCategory;
import com.ublblog.service.ArticleCategoryService;
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "/spring/application-config.xml" })
public class ArticleCategoryServiceImplTest {
	
	@Autowired
	private ArticleCategoryService articleCatService;

	

	
	@Test
	public void testUpdateSort()
	{
		ArticleCategory cat = new ArticleCategory();
		cat.setName("生活日常");
		cat.setId(2);
		boolean result = articleCatService.updateCategory(cat);
		assertTrue(result);
	}

}
