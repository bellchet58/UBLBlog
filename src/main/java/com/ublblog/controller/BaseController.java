package com.ublblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ublblog.constants.ResponseResult;
import com.ublblog.model.ArticleComment;
import com.ublblog.model.ExtendPage;
import com.ublblog.model.FriendlyLink;
import com.ublblog.service.ArticleCategoryService;
import com.ublblog.service.ArticleCommentService;
import com.ublblog.service.ArticleService;
import com.ublblog.service.ArticleTagService;
import com.ublblog.service.ExtendPageService;
import com.ublblog.service.FriendlyLinkService;
import com.ublblog.service.UserService;

public class BaseController extends ResponseResult {
	
	@Autowired
	protected ArticleService articleService;
	
	@Autowired
	protected ArticleTagService articleTagService;
	
	@Autowired
	protected FriendlyLinkService friendlyLinkService;
	
	@Autowired
	protected ArticleCategoryService articleCategoryService;
	
	@Autowired
	protected ExtendPageService extendPageService;
	
	@Autowired
	protected ArticleCommentService articleCommentService;
	
	@Autowired
	protected UserService userService;
	
	protected Logger logger = LoggerFactory.getLogger("com.ublblog.controller.BaseController");
	
	
}
