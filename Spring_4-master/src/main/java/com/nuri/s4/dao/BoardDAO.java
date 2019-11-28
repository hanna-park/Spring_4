package com.nuri.s4.dao;

import java.util.List;

import com.nuri.s4.model.BoardVO;
import com.nuri.s4.util.Pager;

public interface BoardDAO {
	
	// list
	public abstract List<BoardVO> boardList(Pager pager) throws Exception;
	
	// select
	public abstract BoardVO boardSelect(BoardVO boardVO) throws Exception;

	// Write
	public int boardWrite(BoardVO boardVO) throws Exception;
	
	// Update
	public int boardUpdate(BoardVO boardVO) throws Exception;
	
	// Delete
	public int boardDelete(BoardVO boardVO) throws Exception;
	
	// Count
	public int boardCount(Pager pager)throws Exception;

}
