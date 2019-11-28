package com.nuri.s4;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.nuri.s4.dao.BoardNoticeDAO;
import com.nuri.s4.dao.BoardQnaDAO;
import com.nuri.s4.model.BoardVO;

public class BoardNoticeTest extends TestAbstractCase{
	
	@Inject
	private BoardNoticeDAO boardNoticeDAO;
	@Inject
	private BoardQnaDAO boardQnaDAO;
	
	//@Test
	public void boardWriteTest()throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("Test title");
		boardVO.setWriter("ck");
		boardVO.setContents("hi");
		int result = boardNoticeDAO.boardWrite(boardVO);
		assertEquals(1, result);
	}
	
	//@Test
	public void boardWriteTest2()throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("Test title");
		boardVO.setWriter("ck");
		boardVO.setContents("hi");
		int result = boardQnaDAO.boardWrite(boardVO);
		assertEquals(1, result);
	}
	@Test
	public void boardUpdateTest() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("ck");
		boardVO.setContents("ck");
		boardVO.setNum(239);
		int result = boardNoticeDAO.boardUpdate(boardVO);
		assertEquals(1, result);
	}
	

}
