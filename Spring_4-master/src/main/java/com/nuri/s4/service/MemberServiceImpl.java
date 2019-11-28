package com.nuri.s4.service;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.nuri.s4.dao.MemberDAOImpl;
import com.nuri.s4.model.MemberVO;
import com.nuri.s4.util.FileSaver;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAOImpl memberDAOImpl;
	
	@Override
	public MemberVO memberIdCheck(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberIdCheck(memberVO);
	}
	
	@Override
	public int memberJoin(MemberVO memberVO, HttpSession session) throws Exception {
		// Server Hdd에 파일 저장
		// 1. 파일을 저장할 실제 경로
		String realPath = session.getServletContext().getRealPath("resources/upload/member");
		
		FileSaver fs = new FileSaver();
		String fileName = fs.save3(realPath, memberVO.getFile());
//		String fileName = fs.save(realPath, memberVO.getFile());
		memberVO.setFileName(fileName);
		memberVO.setOriginalName(memberVO.getFile().getOriginalFilename());
		return memberDAOImpl.memberJoin(memberVO);
	}

	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberLogin(memberVO);
	}

	@Override
	public int memberUpdate(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberUpdate(memberVO);
	}

	@Override
	public int memberDelete(MemberVO memberVO) throws Exception {
		return memberDAOImpl.memberDelete(memberVO);
		
	}

	@Override
	public int memberPointUpdate(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
