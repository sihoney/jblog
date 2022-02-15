package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;
import com.javaex.vo.SearchVo;

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
	
	public List<SearchVo> searchPost(Map<String, String> map) {

		String kwdOpt = map.get("kwdOpt");
		String keyword = map.get("keyword");
		
		if(kwdOpt.equals("optName")) {

			return postDao.searchPostByName(keyword);
		} else {

			return postDao.searchPostByTitle(keyword);
		}
	}
}
