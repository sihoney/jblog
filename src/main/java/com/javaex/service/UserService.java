package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UsersVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	BlogDao blogDao;
	
	public int idCheck(String id) {
		UsersVo uvo = userDao.idCheck(id);
		
		if(uvo == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public int addUser(UsersVo uvo) {
		userDao.addUser(uvo);
		return blogDao.addBlog(uvo.getId());
	}
	
	public UsersVo login(UsersVo uvo) {
		return userDao.checkUser(uvo);
	}
}
