package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CateVo;

@Service
public class CategoryService {

	@Autowired
	CategoryDao cateDao;
	
	public CateVo addCate(CateVo cvo) {
		cateDao.addCate(cvo);
		
		int no = cvo.getCateNo();
		
		return cateDao.getCateVo(no);
	}
	
	public List<CateVo> getCateList(String id) {
		return cateDao.getCateList(id);
	}
	
	public void deleteCate(int cateNo) {
		cateDao.deleteCate(cateNo);
	}
}
