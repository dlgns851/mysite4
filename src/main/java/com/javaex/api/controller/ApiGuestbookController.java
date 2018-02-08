package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestVo;

@Controller
public class ApiGuestbookController {
	@Autowired
	private GuestBookService guestBookService;
	
	@ResponseBody
	@RequestMapping(value="/gb/api/list", method=RequestMethod.POST)
	public List<GuestVo> apiList(@RequestParam("page") int page) {
		
		
		
		System.out.println(page);
		
		List<GuestVo> guestList =guestBookService.getListAll(page);
		System.out.println(guestList.toString());
		return guestList;
	}
	
	@ResponseBody
	@RequestMapping(value="/gb/api/insertGuestBook", method=RequestMethod.POST)
	public GuestVo apiNewWriting(@ModelAttribute GuestVo guestVo) {
		
		
		guestBookService.insert(guestVo);
		  //입력받은 guestVo로 DB에 입력된 guestVo 뽑아내기 
		
		return guestBookService.selectNew(guestVo); //이름 비밀번호 내용이같은 칼럼을 뽑아서 반환   문제는 . . .. 
		
		//List<GuestVo> guestList =guestBookService.getListAll();
		//System.out.println(guestList.toString());
	
	}
	@ResponseBody
	@RequestMapping(value="/gb/api/deleteGuestBook", method=RequestMethod.POST)
	public int apiDeleteGuest(@ModelAttribute GuestVo guestVo) {
		System.out.println("apideleteguest "+guestVo.toString());
		
		guestBookService.delete2(guestVo);
		  //입력받은 guestVo로 DB에 입력된 guestVo 뽑아내기 
		int no = guestVo.getNo();
		return no; //이름 비밀번호 내용이같은 칼럼을 뽑아서 반환   문제는 . . .. 
		
		//List<GuestVo> guestList =guestBookService.getListAll();
		//System.out.println(guestList.toString());
	
	}
	
	
	/*@ResponseBody
	@RequestMapping(value="/gb/api/list", method=RequestMethod.GET)
	public void apiList() {
		System.out.println("리스폰스바디");
		List<GuestVo> guestList =guestBookService.getListAll();
		System.out.println(guestList.toString());
	
	}*/

}
