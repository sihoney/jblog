package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {
	
	@Autowired
	CommentDao cmtDao;
	
	public CommentVo addCmt(CommentVo cmtVo) {
		cmtDao.addCmt(cmtVo);
		return cmtDao.getCmtVo(cmtVo.getCmtNo());
	}
	
	public List<CommentVo> getList(int postNo) {
		System.out.println("CommentService.getList()");
		return cmtDao.getList(postNo);
	}
	
	public int delete(int cmtNo) {
		System.out.println(cmtNo);
		
		return cmtDao.delete(cmtNo);

	}
}
