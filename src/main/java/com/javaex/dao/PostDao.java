package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;
import com.javaex.vo.SearchVo;

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
	
	public List<PostVo> getPostList(String userId, int startNum, int endNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		return sqlSession.selectList("postbook.getPostList", map);

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
	
	public int totalCnt(String userId) {
		return sqlSession.selectOne("postbook.totalCnt", userId);
	}
	
	public List<SearchVo> searchPostByTitle(String title) {
		System.out.println("title: "+ title);
		return sqlSession.selectList("postbook.searchPostByTitle", title);
	}
	
	public List<SearchVo> searchPostByName(String name) {
		System.out.println("name: " + name);
		return sqlSession.selectList("postbook.searchPostByName", name);
	}

}
