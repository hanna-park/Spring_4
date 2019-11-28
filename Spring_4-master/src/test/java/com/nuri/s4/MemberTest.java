package com.nuri.s4;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.nuri.s4.dao.MemberDAOImpl;
import com.nuri.s4.model.MemberVO;

public class MemberTest extends TestAbstractCase {
	
	@Inject
	private MemberDAOImpl memberDAOImpl;
	
	//@Test
	public void test() throws Exception{
		MemberVO memberVO= new MemberVO();
		memberVO.setId("q1");
		memberVO.setPw("q1");
		memberVO.setName("ff");
		memberVO.setBirth("94.09.02");
		memberVO.setGender("F");
		memberVO.setEmail("wls@naver.com");
		int result = memberDAOImpl.memberJoin(memberVO);
		assertEquals(1, result);
	}
	
	@Test
	public void updateTest() throws Exception{
		MemberVO memberVO= new MemberVO();
		memberVO.setId("ck");
		memberVO.setPw("q2");
		memberVO.setName("ffff");
		memberVO.setBirth("99.09.09");
		memberVO.setGender("m");
		memberVO.setEmail("wls@wls.com");
		
		int result = memberDAOImpl.memberUpdate(memberVO);
		assertEquals(1, result);
		
	}

}
