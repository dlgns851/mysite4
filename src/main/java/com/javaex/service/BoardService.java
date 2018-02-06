package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.util.Paging;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	
	@Autowired
	private BoardDao boardDao;
	
public List<BoardVo> getListAll(String searchWord,Paging paging) {
	
	
	if(searchWord!=null) {									//검색시 if문
		 
		return boardDao.getListAll(searchWord);
	}
	
	
	List<BoardVo> boardList=boardDao.getListAll(paging);
	
	return boardList;
		
	}

public Paging getPaging(String selectedPage) {
	///페이징시작//
	 Paging paging=new Paging();
	
	int countRow=boardDao.countRow();//전체 글수 가져오기
	paging.setWriting_Count(countRow);
	String curPageStr= selectedPage; //선택된 페이지 번호 가져오기
	
	// 전체 글수 가져오는 다오만들고 페이징 객체에 넣어주는거 까지 했음 다음에 할건 페이징 이용해서 겟리스트올 쿼리문 조작!
	int curPage;
	if(curPageStr==null) {
		paging.setCur_Page(1);    //기본값 1
	}
	else {
		curPage=Integer.parseInt(curPageStr);
		paging.setCur_Page(curPage);
	}
	paging.setWritingStart(paging.getWriting_Start());
	paging.setWritingEnd(paging.getWriting_End());
	return paging;
}

public void insertBoard(BoardVo boardVo) {
	boardDao.insertBoard(boardVo);
}

public BoardVo selectView(BoardVo boardVo) {
	return boardDao.selectView(boardVo);
}
public BoardVo selectView2(int no) {
	return boardDao.selectView2(no);
}

public void modifyBoard(BoardVo boardVo) {
	boardDao.modifyBoard(boardVo);
}

public void deleteBoard(int no) {
	boardDao.deleteBoard(no);
}
public void upHit(int no) {
	boardDao.upHit(no);
}
}
