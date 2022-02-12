package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public void addCmt(CommentVo cmtVo) {
		int count = sqlSession.insert("commentbook.addCmt", cmtVo);
		System.out.println(count +"건 코멘트가 저장되었습니다.");
	}
	
	public CommentVo getCmtVo(int cmtNo) {
		return sqlSession.selectOne("commentbook.getCmtVo", cmtNo);
	}
	
	public List<CommentVo> getList(int postNo) {
		System.out.println("CommentDao.getList()");
		
		System.out.println(postNo);
		
		return sqlSession.selectList("commentbook.getList", postNo);
	}
	
	public int delete(int cmtNo) {
		int count = sqlSession.delete("commentbook.delete", cmtNo);
		System.out.println(count + "건 코멘트가 삭제되었습니다.");
		
		return count;
	}
}
