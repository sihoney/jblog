package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	SqlSession sqlSession;
	
	public int addBlog(String id) {
		int count = sqlSession.insert("blogbook.addBlog", id);
		System.out.println(count + "건 블로그가 생성되었습니다.");
		
		return count;
	}
	
	public BlogVo getBlog(String userId) {
		return sqlSession.selectOne("blogbook.getBlog", userId);
	}
	
	public void modifyBlog(BlogVo bvo) {
		int count = sqlSession.update("blogbook.modifyBlog", bvo);
		System.out.println(count + "건 blog가 수정되었습니다.");
	}
	
	public String getBlogTitle(String id) {
		return sqlSession.selectOne("blogbook.getBlogTitle", id);
	}
	
	public String getLogoFile(String id) {
		return sqlSession.selectOne("blogbook.getLogoFile", id);
	}
}
