package com.ublblog.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.ublblog.controller.BaseController;
import com.ublblog.dto.CommentsDto;
import com.ublblog.model.ArticleComment;
import com.ublblog.utils.Configure;
import com.ublblog.utils.RegexpCheckUtils;

@Controller
@RequestMapping("/comment")
public class ArticleCommentController extends BaseController{
	
	/**
     * 
     * 获取所以的文章列表，按照时间降序排列
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/load")
    public ModelAndView getArticleComments(@RequestParam int articleId,@RequestParam int currentPage,@RequestParam int pageSize) {
//        int pageNum = pageInfo.getPageNum()==0?1:pageInfo.getPageNum();
        ModelAndView response = new ModelAndView("/comment");
        List<CommentsDto> comments = null;
        try {  
            comments = articleCommentService.getArticleComment(articleId, currentPage);
        } catch (Exception e) {
            logger.error("ArticleController.getArticleComments();", e.getMessage());
        } 
        PageInfo<CommentsDto> page = new PageInfo<CommentsDto>(comments);
        response.addObject("comments", comments);   
        response.addObject("page", page);  
        return response;
    }
    

	/**
	 * 添加新的文章评论
	 * 暂时未添加邮件回复提醒
	 * @param comment
	 * @return String
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addArticleComment(ArticleComment comment, 
			HttpServletRequest request) {
		
		try {
			//如果博客地址不为空
			if(!comment.getBlogUrl().isEmpty()) {				
				if (!RegexpCheckUtils.checkWebSite(comment.getBlogUrl())) {
					return "INVALIDE_URL";
				}
			}
			//如果用户名为空，则默认为匿名
			if (comment.getUserName().isEmpty()) comment.setUserName("匿名");
			//去除内容里面的html标签元素
			comment.setContent(RegexpCheckUtils.htmlRemoveTag(comment.getContent()));
			//如果成功添加文章，则返回true
			if (articleCommentService.addComment(comment)) { 
				startSendEmail(comment,request);			
				return SUCCESS; 
			}						
		} catch (Exception e) { 
            logger.error("ArticleController.addArticleComment();", e.getMessage());
		}	
		return FAIL;
	}
	
	/**
	 * 
	 * @param tag
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String deleteTag(ArticleComment comment) {
		try {
			if (articleCommentService.deleteComment(comment)) {
				return SUCCESS;
			}
		} catch (Exception e) {
			logger.error("ArticleTagController.deleteTag()",e.getMessage());
		}
		return FAIL;
	}
//	发送通知尚未完成
	private class Email extends Thread {	
		private ArticleComment comment;
		private String readUrl;
	
		public Email(ArticleComment comment, String readUrl) {
			super();
			this.comment = comment;
			this.readUrl = readUrl;
		}

		@Override
		public void run() {
			try {
				articleCommentService.notifyByEmail(comment, readUrl);
			} catch (Exception e) {
				logger.error("ArticleController.Email.send();", e.getMessage());
			}
		}		
	}
	private void startSendEmail(ArticleComment comment,HttpServletRequest request)
	{
		String domain = Configure.getInstance().getProperty("blog_domain");
		// 发送评论邮件
		String readUrl = "";
		if (!domain.equals("")) {
			readUrl = "http://"+domain+"/article/read?id="+
					comment.getArticleId();
		} else {
			readUrl = request.getScheme()+"://"+
					request.getServerName()+":"+request.getServerPort()+
					request.getContextPath()+"/article/read?id="+
					comment.getArticleId();					
		}
		new Email(comment, readUrl).start();	
	}
}
