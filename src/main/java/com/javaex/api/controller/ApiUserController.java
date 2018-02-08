package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
public class ApiUserController {
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping(value="api/user/emailcheck",method=RequestMethod.POST)
	public boolean emailCheck(@RequestBody UserVo userVo) {//리퀘스트바디로 객체 받아오기
		
		System.out.println(userVo.toString());
		
		
		return true;   
	}
	
	
	/*@ResponseBody
	@RequestMapping(value="api/user/emailcheck",method=RequestMethod.POST)
	public boolean emailCheck(@RequestBody UserVo userVo) {
		
		System.out.println(userVo.toString());
		
		
		return true;   
	}*/
	
	/*@ResponseBody
	@RequestMapping(value="api/user/emailcheck",method=RequestMethod.POST)
	public boolean emailCheck(@RequestParam("email") String email) {
		
		boolean result =userService.emailCheck(email);
		System.out.println(email);
		
		
		return result;  //에이잭스로  서버에서 사용자로 객체를 보내려 할때  자바객체를 자바스크립트 객체로 담아야 되는것에 대해 생각해보기  -> json 파싱   
	}*/
}
