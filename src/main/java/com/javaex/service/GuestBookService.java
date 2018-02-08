package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;

@Service
public class GuestBookService {

	@Autowired
	GuestDao guestDao;
	
	public List<GuestVo> getListAll(){
		
		return guestDao.getListAll();
	}
public List<GuestVo> getListAll(int page){
		
		return guestDao.getListAll(page);
	}

public void insert (GuestVo guestVo) {
	guestDao.insert(guestVo);
}

public GuestVo selectNew (GuestVo guestVo) {
	return guestDao.selectNew(guestVo);
}

public void delete2 (GuestVo guestVo) {
	guestDao.delete2(guestVo);
}
	
}
