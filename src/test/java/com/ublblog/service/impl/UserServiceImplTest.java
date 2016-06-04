package com.ublblog.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ublblog.model.User;
import com.ublblog.model.UserWithBLOBs;

public class UserServiceImplTest {

	@Test
	public void testUpdateLogin() {
		UserWithBLOBs user = new UserWithBLOBs();
		user.setShowName("bellchet58*");
		user.setEmail("example@hotmail.com");
		user.setImage("example.com/avatar.jpg");
		user.setDescription("Harmonica");
		
	
	}


}
