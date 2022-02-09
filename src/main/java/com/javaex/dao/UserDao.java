package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UsersVo;

@Repository
public class UserDao {

	@Autowired
	SqlSession sqlSession;
	
	public UsersVo idCheck(String id) {
		return sqlSession.selectOne("usersbook.idCheck", id);
	}
	
	public void addUser(UsersVo uvo) {
		int count = sqlSession.insert("usersbook.addUser", uvo);
		System.out.println(count + "건 - 계정이 생성되었습니다.");
	}
	
	public UsersVo checkUser(UsersVo uvo) {
		System.out.println("uvo: " + uvo);
		return sqlSession.selectOne("usersbook.checkUser", uvo);
	}
}
