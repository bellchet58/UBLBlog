package com.ublblog.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.ublblog.dto.CommentsDto;
import com.ublblog.model.ArticleComment;

public interface ArticleCommentService {

	/**
	 * 添加评论
	 * @param comment
	 * @return boolean
	 */
	public boolean addComment(ArticleComment comment);
	
	/**
	 * 删除评论
	 * @param comment
	 * @return boolean
	 */	
	public boolean deleteComment(ArticleComment comment);
    
	/**
	 * 检索文章相应的评论列表
	 * @param articleId
	 * @return PageResultDto
	 */
	public List<CommentsDto> getArticleComment(Integer articleId,int pageNum);


    
    /***
     * 发送评论通知
     * @param comment
     * @param readUrl
     * @throws Exception
     */
    public void notifyByEmail(ArticleComment comment, String readUrl) 
			throws Exception;
}
