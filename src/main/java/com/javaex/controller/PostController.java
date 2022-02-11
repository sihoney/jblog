package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.PostService;
import com.javaex.vo.PostVo;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@ResponseBody
	@RequestMapping("getList")
	public List<PostVo> getPostList(@RequestParam int cateNo) {
		System.out.println("PostController.getPostList()");
		
		return postService.getList(cateNo);
	}
	
	@ResponseBody
	@RequestMapping("/getVo")
	public PostVo getPost(@RequestParam(value="postNo") int postNo) {
		System.out.println("PostController.getPost()");
		
		return postService.getPostVo(postNo);
	}
}
