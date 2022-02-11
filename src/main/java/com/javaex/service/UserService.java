package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UsersVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	BlogDao blogDao;
	@Autowired
	CategoryDao cateDao;
	
	/* 아이디 중복 체크 */
	public int idCheck(String id) {
		UsersVo uvo = userDao.idCheck(id);
		
		if(uvo == null) {
			return 0;
		} else {
			return 1;
		}
	}
	
	/* 회원가입 */
	public int addUser(UsersVo uvo) {
		int count = userDao.addUser(uvo); 
		blogDao.addBlog(uvo.getId());
		cateDao.defaultSetting(uvo.getId());
		
		return count;
	}
	
	/* 로그인 */
	public Map<String, Object> login(UsersVo uvo) {
		String blogTitle = blogDao.getBlogTitle(uvo.getId()); 
		UsersVo authUser =  userDao.checkUser(uvo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("blogTitle", blogTitle);
		map.put("authUser", authUser);
		
		return map;
	}
}
