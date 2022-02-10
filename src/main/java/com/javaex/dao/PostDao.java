package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;

	public void deletePost(int cateNo) {
		int count = sqlSession.delete("postbook.deletePost", cateNo);
		System.out.println(count + "건이 삭제되었습니다.");
	}
}
