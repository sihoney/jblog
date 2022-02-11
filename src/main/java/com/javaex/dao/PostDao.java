package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;

	public void deletePost(int cateNo) {
		int count = sqlSession.delete("postbook.deletePost", cateNo);
		System.out.println(count + "건 글이 삭제되었습니다.");
	}
	
	public void addPost(PostVo pvo) {
		int count = sqlSession.insert("postbook.addPost", pvo);
		System.out.println(count + "건 글이 저장되었습니다.");
	}
	
	public List<PostVo> getPostList(String userId) {
		System.out.println(userId);
		List<PostVo> postList =  sqlSession.selectList("postbook.getPostList", userId);

		return postList;
	}
	
	public PostVo getRecentPost(String userId) {
		return sqlSession.selectOne("postbook.getRecentPost", userId);
	}
	
	public List<PostVo> getPostList(int cateNo) {
		return sqlSession.selectList("postbook.getListByCate", cateNo);
	}
	
	public PostVo getPostVo(int postNo) {
		return sqlSession.selectOne("postbook.getPostVo", postNo);
	}
}
