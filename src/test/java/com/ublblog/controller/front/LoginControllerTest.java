package com.ublblog.controller.front;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ublblog.model.User;
import com.ublblog.service.UserService;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = { "/spring/application-config.xml" })
public class LoginControllerTest {
	
	@Autowired
	private UserService userService;
	SqlSessionFactoryBean bean;
	@Test
	public void testLogin() {
		User user = new User();
		user.setName("bellchet58");
		user.setPassword("tz8888");
		user = userService.getUser(user);
		assertNotNull(user);
		
		assertEquals("bellchet58", user.getName());
		assertEquals("tz8888", user.getPassword());
	}
	@Test
	public void testAuth()
	{
		User user = new User();
		user.setId(1);
		user = userService.getUser(user);
		assertNotNull(user);
		assertEquals("bellchet58", user.getName());
		assertEquals("tz8888", user.getPassword());
	}
}
