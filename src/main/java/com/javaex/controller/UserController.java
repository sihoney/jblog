package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		
		return "user/loginForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method={RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UsersVo uvo, HttpSession session) {
		System.out.println("UserController.login()");
		
		UsersVo authUser = userService.login(uvo);
		
		if(authUser == null) {
			return "fail";
		} else {
			session.setAttribute("authUser", authUser);
			return "success";
		}
	}
	
	@RequestMapping(value="/logout", method={RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
			
		session.removeAttribute("authUser");
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@RequestBody UsersVo uvo ) {
		System.out.println("UserController.join()");
		System.out.println(uvo);
		
		int count = userService.addUser(uvo);
		
		if(count == 1) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@ResponseBody
	@RequestMapping("/idCheck")
	public int idCheck(@RequestParam(value="id") String id) {
		System.out.println("UserController.idCheck()");
		
		return userService.idCheck(id);
	}
	
	@RequestMapping("/joinSuccess")
	public String joinSuccess() {
		System.out.println("UserController.joinSuccess()");
		
		return "user/joinSuccess";
	}
}
