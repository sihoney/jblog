package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	PostDao postDao;
	
	public List<PostVo> getList(int cateNo) {
		List<PostVo> postList = postDao.getPostList(cateNo);
		return postList;
	}
	
	public PostVo getPostVo(int postNo) {
		return postDao.getPostVo(postNo);
	}
}
