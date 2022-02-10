package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping(value="/{userId}")
	public String blog(@PathVariable("userId") String userId, Model model) {
		System.out.println("BlogController.blog()");

		model.addAttribute("map", blogService.getBlogInfo(userId));
		
		return "blog/blog-main";
	}
	
	@RequestMapping("/{userId}/admin/basic")
	public String adminBasic() {
		System.out.println("BlogController.adminBasic()");
		
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping("/{userId}/admin/cate")
	public String adminCate(Model model, @PathVariable(value="userId") String id) {
		System.out.println("BlogController.adminCate()");
		/*
		List<CateVo> cateList = blogService.getCateList(id);
		*/
		model.addAttribute("id", id);
		
		return "blog/admin/blog-admin-cate";
	}
	
	@RequestMapping("/{userId}/admin/write")
	public String adminWrite() {
		System.out.println("BlogController.adminWrite()");
		
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping(value="/{userId}/admin/basic/modify") 
	public String modify(@ModelAttribute BlogVo bvo, @PathVariable(value="userId") String id) {
		System.out.println("BlogController.modify()");

		bvo.setId(id);
		blogService.modifyBlog(bvo);
		
		return "redirect:/"+id;
	}
}
