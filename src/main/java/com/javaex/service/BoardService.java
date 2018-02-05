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
	
public List<BoardVo> getListAll() {
		
	List<BoardVo> boardList=boardDao.getListAll();
	
	return boardList;
		
	}

public void insertBoard(BoardVo boardVo) {
	boardDao.insertBoard(boardVo);
}

public BoardVo selectView(BoardVo boardVo) {
	return boardDao.selectView(boardVo);
}
}
