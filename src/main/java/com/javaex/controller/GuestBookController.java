package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestDao;
import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping("gb")
public class GuestBookController {
	@Autowired
	GuestDao dao ;
	@Autowired
	GuestBookService guestBookService;
	@RequestMapping("/list")
	public String list(Model model) {
		
		model.addAttribute("guestList", guestBookService.getListAll());
		return "guestbook/list";
	}
	
	
	
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GuestVo guestVo  ) {
	

		
		guestBookService.insert(guestVo);
		

		return "redirect:/gb/list";

	}
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestVo guestVo ) {
		
		guestBookService.delete2(guestVo);
		
		return "redirect:/gb/deleteform?no2="+guestVo.getNo();
	}
	
	@RequestMapping("deleteform")
	public String deleteform() {
		return "guestbook/deleteform";
	
	} 
	
	@RequestMapping(value="/listajax", method=RequestMethod.GET)
	public String listajax() {
		return "guestbook/listajax";
	}
	

}
