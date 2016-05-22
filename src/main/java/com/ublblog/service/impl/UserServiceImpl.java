package com.ublblog.service.impl;

import javax.jws.soap.SOAPBinding.Use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ublblog.dao.UserDao;
import com.ublblog.dao.UserMapper;
import com.ublblog.model.ExtendPageExample;
import com.ublblog.model.User;
import com.ublblog.model.UserExample;
import com.ublblog.model.UserWithBLOBs;
import com.ublblog.service.UserService;
import com.ublblog.utils.CryptUtils;

@Service
public class UserServiceImpl implements UserService{
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserDao userDao;
	
	
	
	@Override
	public User getUser(User user) {
		if(user.getPassword()!= null)
		{
//			对密码加密，进行查询
			user.setPassword(CryptUtils.encryptString(user.getPassword()));
			logger.debug(user.getPassword());
			logger.debug(user.getName());
		}	
		user =  getUser(userMapper,user);
		if(user != null)
		{
//			检索完用户后，对密码重新解密后设置
			user.setPassword(CryptUtils.decryptString(user.getPassword()));
		}
		return user;
	}

	@Override
	public boolean updateUser(User user){
		if(user.getPassword() != null)
		{
			user.setPassword(CryptUtils.encryptString(user.getPassword()));
			
		}
		int result = userDao.updateUserinfo(user);
		return result>0;
	}
	
	private UserExample getUserExample(User user)
	{
		UserExample exam = new UserExample();
		if(user.getName() != null)
			exam.or().andNameEqualTo(user.getName());
		if(user.getId() != null)
			exam.or().andIdEqualTo(user.getId());
		if(user.getPassword() != null)
			exam.or().andPasswordEqualTo(user.getPassword());
		return exam;
	}
	
	private  UserWithBLOBs getUser(UserMapper userMapper,User user)
	{
		return userMapper.selectByExampleWithBLOBs(getUserExample(user)).get(0);
	}
	
	private int updateUser(UserMapper userMapper,User user)
	{
		return userMapper.updateByExample(user, getUserExample(user));
	}

}
