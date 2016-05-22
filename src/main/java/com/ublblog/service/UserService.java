package com.ublblog.service;

import org.springframework.stereotype.Service;

import com.ublblog.model.User;


public interface UserService {
	 /**
     * 
     * 根据用户对象检索用户信息
     * @param user
     * @return User
     * @see [类、类#方法、类#成员]
     */
    public User getUser(User user);
    
    /**
     * 更新用户信息
     * @param user
     * @return boolean
     */
    public boolean updateUser(User user);
}
