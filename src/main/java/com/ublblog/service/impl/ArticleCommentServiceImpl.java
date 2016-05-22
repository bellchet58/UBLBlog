package com.ublblog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ublblog.constants.Common;
import com.ublblog.dao.ArticleCommentMapper;
import com.ublblog.dao.ArticleDao;
import com.ublblog.dto.ArticleDto;
import com.ublblog.dto.CommentsDto;
import com.ublblog.model.Article;
import com.ublblog.model.ArticleComment;
import com.ublblog.model.ArticleCommentExample;
import com.ublblog.service.ArticleCommentService;
import com.ublblog.utils.Configure;
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	@Autowired
	private ArticleCommentMapper articleCommMapper;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public boolean addComment(ArticleComment comment) {
//		默认为0
		if(comment.getCreateTime() == null)
				comment.setCreateTime(new Date());
		if(comment.getFatherComm() == null)
			comment.setFatherComm(0);
		int result = articleCommMapper.insert(comment);
		return result>0;
	}

	@Override
	public boolean deleteComment(ArticleComment comment) {
		ArticleCommentExample exam = new ArticleCommentExample();
		exam.or().andIdEqualTo(comment.getId());
		exam.or().andFatherCommEqualTo(comment.getId());
		int result = articleCommMapper.deleteByExample(exam);
		return result>0;
	}

	@Override
	public List<CommentsDto> getArticleComment(Integer articleId, int pageNum) {
		int pageSize = 15;
		int fatherComm = Common.ORIGINAL_COMMENT;
		PageHelper.startPage(pageNum,pageSize,"create_time asc");
//		检索原始评论
		List<ArticleComment> list = articleCommMapper.selectByExampleWithBLOBs(getArticleCommentExample(articleId,fatherComm));
		List<CommentsDto> listDto = new ArrayList<CommentsDto>();
		//		检索回复评论
		for (ArticleComment comm : list) {
    		CommentsDto dto = new CommentsDto();
    		//先添加当前的评论
    		dto.setComment(comm);
    		//如果此评论为非恢复性内容
    		//根据fatherID获取评论列表
    		int fatherId = comm.getId();
    		List<ArticleComment> childComm = articleCommMapper.selectByExampleWithBLOBs(getArticleCommentExample(fatherId));
    		//如果数据不为空，则添加
    		if (!childComm.isEmpty()) 
    			dto.setFeedBack(childComm);    		
	    	//添加进结果List
	    	listDto.add(dto);
    	}
		return listDto;
	}

	@Override
	public void notifyByEmail(ArticleComment comment, String readUrl) throws Exception {
//		Article newArt = new Article();
//		newArt.setId(comment.getArticleId());
//		ArticleDto article = articleDao.selectArticle(newArt);
//		String title = "<a href=\""+readUrl+"\">"+article.getTitle()+"</a>";
//		StringBuilder contentFir = new StringBuilder(),
//				contentSec = new StringBuilder();
//		String userName = "";
//		if(comment.getUserName().isEmpty())
//		{
//			userName = "匿名用户在你的文章";
//		}else 
//		{
//			if (!comment.getBlogUrl().isEmpty()) {
//				userName = "<a href=\""+comment.getBlogUrl()+"\">用户:@"
//						+comment.getUserName()+"</a>";
//			} else {
//				userName = "用户:@"+comment.getUserName();
//			}
//		}
//		contentFir.append(userName);
//		contentFir.append("在您的文章");
//		contentFir.append(title);
//		contentFir.append("中留下了评论！<br>内容：");
//		contentFir.append(comment.getContent());
//		String account = Configure.getInstance().getProperty("default_receiver");
//		// 先给站长发送email通知	
//				EmailService.send("博客评论", contentFir.toString(), account);
//				
//				// 如果是回复评论，则给原品论这发送email信息
//				if (comment.getFatherComm() != null) {
//					ArticleComment fathCom = articleCommentDao.getCommentById(
//							comment.getFatherComm());
//					contentSec.append("您在文章");
//					contentSec.append(title);
//					contentSec.append("中的评论有了");
//					contentSec.append(userName);
//					contentSec.append("的回复！<br>内容：");
//					contentSec.append(comment.getContent());
//					if (!fathCom.getEmail().isEmpty()) {
//						EmailService.send("博客评论回复", contentSec.toString(),
//								fathCom.getEmail());
//					}
//				}
			
		
		
	}
	
	private ArticleCommentExample getArticleCommentExample(int articleId,int fatherComm){
		ArticleCommentExample exam = new ArticleCommentExample();
		exam.or().andArticleIdEqualTo(articleId).andFatherCommEqualTo(fatherComm);
		return exam;
	}
	private ArticleCommentExample getArticleCommentExample(int fatherComm){
		ArticleCommentExample exam = new ArticleCommentExample();
		exam.or().andFatherCommEqualTo(fatherComm);
		return exam;
	}

}
