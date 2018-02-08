package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public UserVo login(String email, String password) {
		
		
		return userDao.getUser(email,password);
	}
	
	public void join(UserVo userVo) {
		
		userDao.joinUser(userVo);
		
	}
	
	public void modify(UserVo userVo) {
		
		userDao.modifyUser(userVo);
		
	}
	
	public boolean emailCheck(String email) {
		boolean result;
		UserVo userVo = userDao.selectUser(email);
		
		if(userVo==null)
			result= true;
		else
			result= false;
		
		return result;
	}
}
