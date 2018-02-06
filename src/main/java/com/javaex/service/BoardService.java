package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	
	@Autowired
	private BoardDao boardDao;
	
public List<BoardVo> getListAll(String searchWord) {
	
	
	if(searchWord!=null) {									//검색시 if문
		 
		return boardDao.getListAll(searchWord);
	}
	
	List<BoardVo> boardList=boardDao.getListAll();
	
	return boardList;
		
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
