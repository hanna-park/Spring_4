package com.nuri.s4.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nuri.s4.dao.BoardNoticeDAO;
import com.nuri.s4.dao.NoticeFilesDAO;
import com.nuri.s4.model.BoardNoticeVO;
import com.nuri.s4.model.BoardVO;
import com.nuri.s4.model.FilesVO;
import com.nuri.s4.util.FileSaver;
import com.nuri.s4.util.Pager;

@Service
public class BoardNoticeService implements BoardService {

	@Inject
	private BoardNoticeDAO boardNoticeDAO;
	
	@Inject
	private FileSaver fs;
	
	@Inject
	private NoticeFilesDAO noticeFilesDAO;
	
	public boolean summerFileDelete(String file, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload/summerFile");
		return fs.fileDelete(realPath, file);
	}
	
	public String summerFile(MultipartFile file, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload/summerFile");
		return fs.save2(realPath, file);
	}
	
	public FilesVO fileSelect(FilesVO noticeFilesVO)throws Exception {
		return noticeFilesDAO.fileSelect(noticeFilesVO);
	}
	
	public int fileWrite(FilesVO noticeFilesVO) throws Exception{
		return noticeFilesDAO.fileWrite(noticeFilesVO);
	}
	
	public int fileDelete(FilesVO noticeFilesVO)throws Exception {
		return noticeFilesDAO.fileDelete(noticeFilesVO);
	}
	
	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {
		pager.makeRow();
		pager.makePage(boardNoticeDAO.boardCount(pager));
		return boardNoticeDAO.boardList(pager);
	}

	@Override
	public BoardVO boardSelect(BoardVO boardVO) throws Exception {
		// boardVO = boardNoticeDAO.boardSelect(boardVO);
		// BoardNoticeVO boardNoticeVO = (BoardNoticeVO)boardVO;
		
		// List<NoticeFilesVO> ar = noticeFilesDAO.fileList(boardVO.getNum());
		
		// boardNoticeVO.setFiles(ar); 
		
		return boardNoticeDAO.boardSelect(boardVO);
	}

	@Override
	public int boardWrite(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		// server HDD save
		// 1. realPath
		String realPath = session.getServletContext().getRealPath("resources/upload/notice");
		FilesVO noticeFilesVO = new FilesVO();
		int result = boardNoticeDAO.boardWrite(boardVO);
		
		for(MultipartFile multipartFile : file) {
			if(multipartFile.getOriginalFilename() != "") {
				String fileName = fs.save(realPath, multipartFile);
				noticeFilesVO.setNum(boardVO.getNum());
				noticeFilesVO.setFname(fileName);
				noticeFilesVO.setOname(multipartFile.getOriginalFilename());
				noticeFilesDAO.fileWrite(noticeFilesVO);
			}
		}
		return result;
	}

	@Override
	public int boardUpdate(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception {
		
		String realPath = session.getServletContext().getRealPath("resource/upload/notice");
		int result = boardNoticeDAO.boardUpdate(boardVO);
		FilesVO noticeFilesVO = new FilesVO();
		
		for(MultipartFile multipartFile : file) {
			String fileName = fs.save2(realPath, multipartFile);
			noticeFilesVO.setNum(boardVO.getNum());
			noticeFilesVO.setFname(fileName);
			noticeFilesVO.setOname(multipartFile.getOriginalFilename());
			noticeFilesDAO.fileWrite(noticeFilesVO);
		}
		return boardNoticeDAO.boardUpdate(boardVO);
	}

	@Override
	public int boardDelete(BoardVO boardVO) throws Exception {
		return boardNoticeDAO.boardDelete(boardVO);
	}

}
