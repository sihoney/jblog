package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CateVo;

@Repository
public class CategoryDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<CateVo> getCate(String userId) {
		return sqlSession.selectList("catebook.getCate", userId);
	}
	
	public List<CateVo> getCateList(String id) {
		return sqlSession.selectList("catebook.getCateList", id);
	}
	
	public int addCate(CateVo cvo) {
		int count = sqlSession.insert("catebook.addCate", cvo);
		System.out.println(count + "건 카테고리가 추가되었습니다.");
		return count;
	}
	
	public CateVo getCateVo(int cateNo) {		
		return sqlSession.selectOne("catebook.getCateVo", cateNo);
	}
	
	public int deleteCate(int cateNo) {
		System.out.println("CategoryDao.deleteCate()");
		
		int count = sqlSession.delete("catebook.deleteCate", cateNo);
		System.out.println(count + "건이 삭제되었습니다");
		return count;
	}
	
	public void defaultSetting(String id) {
		System.out.println("CateDao.defaultSetting()" + id);
		
		int count = sqlSession.insert("catebook.defaultSetting", id);
		System.out.println(count + "건 미분류 카테고리가 추가되었습니다.");
	}
}
