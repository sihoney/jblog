package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	@Autowired
	CategoryDao cateDao;
	@Autowired
	PostDao postDao;
	
	public Map<String, Object> getBlogInfo(String userId) {
		List<CateVo> cateList = cateDao.getCate(userId);
		BlogVo blogVo =  blogDao.getBlog(userId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cateList", cateList);
		map.put("blogVo", blogVo);
		
		return map;
	}
	
	public void modifyBlog(BlogVo bvo) {
		System.out.println("BlogService.modifyBlog()");
		
		MultipartFile file = bvo.getLogoFile();
		
		String saveDir = "C:\\javaStudy\\file\\upload";
		
		String orgName = file.getOriginalFilename();
		
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		String filePath = saveDir + "\\" + saveName;
		
		System.out.println(filePath);
		
		//blogDao.modifyBlog(bvo);
	}
}
