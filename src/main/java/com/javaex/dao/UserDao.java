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
}
