package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CateVo;
import com.javaex.vo.PostVo;

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
	
	@RequestMapping("/{userId}/admin/basic")   // ******** 사진 선택했을 때 사진 불러오기 & blogTitle 불러오기
	public String adminBasic(@PathVariable(value="userId") String userId, Model model) {
		System.out.println("BlogController.adminBasic()");
		
		BlogVo blogVo = blogService.getBlogVo(userId);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/admin/blog-admin-basic";
	}
	
	@RequestMapping("/{userId}/admin/basic/modify") // *********** 제목 수정하기
	public String modify(@ModelAttribute BlogVo bvo, MultipartFile file, @PathVariable(value="userId") String id) {
		System.out.println("BlogController.modify()");

		bvo.setId(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file", file);
		map.put("bvo", bvo);
		
		blogService.modifyBlog(map);
		
		return "redirect:/"+id;
	}
	
	@RequestMapping("/{userId}/admin/cate")
	public String adminCate(Model model, @PathVariable(value="userId") String id) {
		System.out.println("BlogController.adminCate()");

		model.addAttribute("id", id);
		
		return "blog/admin/blog-admin-cate";
	}
	
	@RequestMapping("/{userId}/admin/write")
	public String adminWrite(@PathVariable(value="userId") String userId, Model model) {
		System.out.println("BlogController.adminWrite()");

		List<CateVo> cateList = blogService.writeForm(userId);

		model.addAttribute("cateList", cateList);
		
		return "blog/admin/blog-admin-write";
	}
	
	@RequestMapping("/{userId}/admin/write/add")
	public String addPost(@ModelAttribute PostVo pvo, @PathVariable(value="userId") String userId) {
		System.out.println("BlogController.addPost()");
		
		/* post 저장 */
		blogService.addPost(pvo);
		
		return "redirect:/" + userId;
	}
	
}
