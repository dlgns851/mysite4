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
import com.javaex.util.Paging;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping("list")
	public String list(Model model,@RequestParam(value="searchword",required=false) String searchWord
			,@RequestParam(value="page",required=false) String selectedPage) { //searchword  없어서 안켜지는데 어케할지 고민 하면됨 
		
		Paging paging =boardService.getPaging(selectedPage);
	
		model.addAttribute("list",boardService.getListAll(searchWord,paging));
		model.addAttribute("paging",paging);

		
		
		
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
		
		BoardVo view = boardService.selectView(boardVo);
		//model.addAttribute(view);     // jsp 에서 view.title 로 못읽음    이형식으로 보낸거 어떻게 읽음 ? 
		
		
		boardService.upHit(boardVo.getNo());
		
		model.addAttribute("view", view);
		
		return "/board/view";
	}
	@RequestMapping("modifyform")
	public String modifyForm(@RequestParam("no") int no,Model model) {
		
		BoardVo view = boardService.selectView2(no);
		model.addAttribute("view", view);
		return "/board/modify";
		
	}
	
	@RequestMapping("modify")
	public String modify(@ModelAttribute BoardVo boardVo,HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser"); //우회접속한 사용자 차단을 위한 authuser
		if(boardVo.getUserNo()!=authUser.getNo())
			return "redirect:/user/loginform";
			
		
		else {
			boardService.modifyBoard(boardVo);
			return "redirect:/board/view?no="+boardVo.getNo();
			}
		
		
	}
	@RequestMapping("delete")
	public String delete(@ModelAttribute BoardVo boardVo,HttpSession session) {
		System.out.println(boardVo.toString());
		UserVo authUser = (UserVo)session.getAttribute("authUser"); //우회접속한 사용자 차단을 위한 authuser
		if(boardVo.getUserNo()!=authUser.getNo())
			return "redirect:/user/loginform";
			
		
		else {
			boardService.deleteBoard(boardVo.getNo());
			System.out.println("delete else문 진입 getNo출력"+boardVo.getNo());
			return "redirect:/board/list";
			}
		
		
	}
	
	
	
	
	
}
