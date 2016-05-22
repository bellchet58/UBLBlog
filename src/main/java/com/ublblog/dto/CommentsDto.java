package com.ublblog.dto;

import java.util.List;

import com.ublblog.model.ArticleComment;

public class CommentsDto {

		//源评论
		private ArticleComment comment;
		//评论回复-评论的评论
		private List<ArticleComment> feedBack;
		
		public ArticleComment getComment() {
			return comment;
		}
		
		public void setComment(ArticleComment comment) {
			this.comment = comment;
		}
		
		public List<ArticleComment> getFeedBack() {
			return feedBack;
		}
		
		public void setFeedBack(List<ArticleComment> feedBack) {
			this.feedBack = feedBack;
		}

}
