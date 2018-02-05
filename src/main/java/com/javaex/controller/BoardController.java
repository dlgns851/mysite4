package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.BoardDao;
import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("list")
	public String list(Model model) {
		
		model.addAttribute("list",boardService.getListAll());
		
		return "board/list";
	}
	
	@RequestMapping("writeform")
	public String wirteFrom(HttpSession session) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		
		if(authUser==null)
			return "user/login"; //글쓰기 버튼을 우회해서 들어온 사용자를 로그인 페이지로 보냄 
		else
		return "board/write";
	}
	
	@RequestMapping("write")
	public String write(@ModelAttribute BoardVo boardVo,HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());   // 로그인된 사용자의 no를 게시물의 userno에 넣는다 
		
		boardService.insertBoard(boardVo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("view")
	public String view(@ModelAttribute BoardVo boardVo,Model model) {
		// 마이바티스 쿼리문 파라미터타입에 인트를 넣는 문법 아직몰라서 임시로 vo로 받음 
		   // 로그인된 사용자의 no를 게시물의 userno에 넣는다 
		System.out.println("선택된 글번호"+boardVo.getNo());
		BoardVo view = boardService.selectView(boardVo);
		//model.addAttribute(view);     // jsp 에서 view.title 로 못읽음    이형식으로 보낸거 어떻게 읽음 ? 
		
		
		model.addAttribute("view", view);
		
		return "/board/view";
	}
	
	
	
	
}
