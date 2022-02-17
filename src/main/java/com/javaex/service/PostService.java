package com.javaex.service;

import java.util.HashMap;
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
	
	public Map<String, Object> searchPost(Map<String, String> map, int crtPage) {

		System.out.println("crtPage: " + crtPage);
		
		int listCnt = 10;
		int startListNo = (crtPage - 1) * listCnt + 1;
		int endListNo = startListNo + listCnt - 1;
		
		String kwdOpt = map.get("kwdOpt");
		String keyword = map.get("keyword");
		
		List<SearchVo> searchList;
		int totalListCnt;
		
		if(kwdOpt.equals("optName")) { // option name
			searchList =  postDao.searchPostByName(keyword, startListNo, endListNo);
			totalListCnt = postDao.getTotalCntByName(keyword);
			
		} else { // option title
			searchList = postDao.searchPostByTitle(keyword, startListNo, endListNo);
			totalListCnt = postDao.getTotalCntByTitle(keyword);
		}
		
		int totalBtnCnt = 5;
		int endBtnNo = (int) (Math.ceil(crtPage / (double)totalBtnCnt)) * totalBtnCnt;
		int startBtnNo = endBtnNo - totalBtnCnt + 1;

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("startBtnNo", startBtnNo);
		map2.put("endBtnNo", endBtnNo);
		map2.put("searchList", searchList);
		
		return map2;
	}
}
