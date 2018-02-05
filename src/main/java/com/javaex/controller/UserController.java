package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
						HttpSession session) {
		
		
		UserVo authUser = userService.login(email, password);
		
		if(authUser != null) {
			session.setAttribute("authUser", authUser);
			return "main/index";
		}
		else {
			
			return "redirect:/user/loginform?flag=1";
		}
		
	}
	
	@RequestMapping("joinform")
	public String joinForm() {
		
		return "user/joinform";
		
	}
	@RequestMapping("join")
	public String join(@ModelAttribute UserVo newUserVo) {
		
		userService.join(newUserVo);
		return "redirect:/user/joinsuccessform?name="+newUserVo.getName();
		
		
	}
	
	@RequestMapping("joinsuccessform")
	public String joinSuccessForm() {
		return "user/joinsuccess";
	}
	
	
	@RequestMapping("modify") 
	public String modify(HttpSession session,
			@ModelAttribute UserVo newUserVo ){
		
	
		
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		int no;
		if(userVo==null) {
			return "user/loginform";
		}
		else {
		no= userVo.getNo();
		
		newUserVo.setNo(no);
		userService.modify(newUserVo);
			
		session.setAttribute("authUser", newUserVo); //세션수정 
		return "redirect:/main";
		}
		
	}
	
	
	@RequestMapping("modifyform")
	public String modifyForm(HttpSession session) {
		UserVo userVo=(UserVo)session.getAttribute("authUser");
		
		if(userVo==null)
			return("user/loginform");
			
		else
		return "user/modifyform";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/main";
	}
	
}
