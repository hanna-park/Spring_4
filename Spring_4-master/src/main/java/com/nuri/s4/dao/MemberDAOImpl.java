package com.nuri.s4.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.nuri.s4.model.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="memberMapper.";
	
	@Override
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"memberIdCheck", memberVO);
	}
	
	@Override
	public int memberJoin(MemberVO memberVO) throws Exception {
		return sqlSession.insert(NAMESPACE+"memberJoin", memberVO);
	}

	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"memberLogin", memberVO);
	}

	@Override
	public int memberUpdate(MemberVO memberVO) throws Exception {
		return sqlSession.update(NAMESPACE+"memberUpdate", memberVO);
				
	}

	@Override
	public int memberDelete(MemberVO memberVO) throws Exception {
		return sqlSession.delete(NAMESPACE+"memberDelete", memberVO);
	}

	@Override
	public int memberPointUpdate(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
