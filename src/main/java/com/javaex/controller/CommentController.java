package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@ResponseBody
	@RequestMapping("/add")
	public CommentVo add(@RequestBody CommentVo cmtVo, HttpSession session) {
		System.out.println("CommentController.add()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		cmtVo.setUserNo(authUser.getUserNo());
		
		return commentService.addCmt(cmtVo);
	}
	
	@ResponseBody
	@RequestMapping("/getList")
	public List<CommentVo> getList(@RequestParam(value="postNo") int postNo){
		System.out.println("CommentController.getList()");
		
		return commentService.getList(postNo);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@RequestParam(value="cmtNo") int cmtNo) {
		System.out.println("CommentController.delete()");
		
		return commentService.delete(cmtNo);
	}
}
