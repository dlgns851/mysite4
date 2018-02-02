package com.javaex.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("loginform")
	public String loginForm() {
		return "user/loginform";
	}
	
	@RequestMapping("login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
						HttpSession session,Model model) {
		
		
		UserVo authUser = userService.login(email, password);
		
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "main/index";
		}
		else {
			String flag="1";
			model.addAttribute(flag);
			return "user/loginform";
			//return "redirect:/user/loginform?flag=1";
		}
	
		
		
	}
}
