package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CateVo;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("cate")
public class CategoryController {
	
	@Autowired
	CategoryService cateService;
	
	@ResponseBody
	@RequestMapping("/add")
	public CateVo add(@RequestBody CateVo cvo, HttpSession session) {
		System.out.println("CategoryController.add()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		cvo.setId(authUser.getId());
		
		return cateService.addCate(cvo);		

	}
	
	@ResponseBody
	@RequestMapping("/getCateList")
	public List<CateVo> getCateList(HttpSession session) {
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		
		List<CateVo> cateList = cateService.getCateList(authUser.getId());
		return cateList;
	}
	
	@ResponseBody
	@RequestMapping("/deleteCate")
	public String deleteCate(@RequestParam(value="cateNo") int cateNo) {
		System.out.println("CategoryController.deleteCate()");
		
		int result = cateService.deleteCate(cateNo);
		
		if(result == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
}
