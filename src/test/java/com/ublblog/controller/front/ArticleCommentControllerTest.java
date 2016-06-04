package com.ublblog.controller.front;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ublblog.dto.CommentsDto;
import com.ublblog.model.UserWithBLOBs;
import com.ublblog.service.ArticleCommentService;
import com.ublblog.service.UserService;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "/spring/application-config.xml" })
public class ArticleCommentControllerTest {
	@Autowired
	private ArticleCommentService articleCommentService;
	@Autowired
	private UserService userService;
	@Test
	public void testGetImage() {
		List<CommentsDto> comments  = null;
		comments = articleCommentService.getArticleComment(1, 1);
		UserWithBLOBs user = null;
        for(CommentsDto comment:comments)
        {
        	user = new UserWithBLOBs();
        	user.setName(comment.getComment().getUserName());
        	user = (UserWithBLOBs) userService.getUser(user);
        }
        assertNotNull(user);
        assertEquals("http://localhost:8080/UBLBlog/02F0C51120F8D4F465B122671EA050A6.jpg", user.getImage());
	}

}
