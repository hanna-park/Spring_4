package com.nuri.s4.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nuri.s4.model.BoardNoticeVO;
import com.nuri.s4.model.BoardQnaVO;
import com.nuri.s4.model.BoardVO;
import com.nuri.s4.model.FilesVO;
import com.nuri.s4.service.BoardNoticeService;
import com.nuri.s4.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	
	@Inject
	private BoardNoticeService boardNoticeService;
	
	@Value("#{db['notice']}")
	private String board;
	
	@ModelAttribute("board")
	public String getBoard() {
		return board;
	}
	
	
	@PostMapping(value = "summerFileDelete")
	public ModelAndView summerFileDelete(String file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean check = boardNoticeService.summerFileDelete(file, session);
		String result = "Delete Fail";
		if(check) {
			result = "Delete Success";
		}
		mv.setViewName("common/common_ajaxResult");
		mv.addObject("result", result);
		
		return mv;
	}
	
	@PostMapping(value = "summerFile")
	public ModelAndView summerFile(MultipartFile file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		String fileName = boardNoticeService.summerFile(file, session);
		mv.setViewName("common/common_ajaxResult");
		mv.addObject("result", fileName);
		return mv;

	}

	
	@GetMapping(value = "fileDown")
	public ModelAndView fileDown(FilesVO filesVO) throws Exception{
		filesVO = boardNoticeService.fileSelect(filesVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("file", filesVO);
		mv.setViewName("fileDown");

		return mv;
	}
	
	public ModelAndView fileWrite(FilesVO filesVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardNoticeService.fileWrite(filesVO);
		mv.setViewName("common/common_ajaxResult");
		mv.addObject("result", result);
		return mv;
	}
	
	@PostMapping(value = "fileDelete")
	public ModelAndView fileDelete(FilesVO noticeFilesVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardNoticeService.fileDelete(noticeFilesVO);
		mv.setViewName("common/common_ajaxResult");
		mv.addObject("result", result);
		return mv;
	}
	
	@RequestMapping(value = "noticeDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardNoticeService.boardDelete(boardVO);
		if(result>0) {
			mv.setViewName("redirect:./noticeList");
		}else {
			mv.setViewName("common/common_result");
			mv.addObject("msg", "Delete Fail");
			mv.addObject("path", "./noticeList");
		}
		return mv;	
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.GET)
	public ModelAndView boardUpdate(BoardVO boardVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = boardNoticeService.boardSelect(boardVO);
		BoardNoticeVO noticeVO = (BoardNoticeVO)boardVO;
		int size = noticeVO.getFiles().size();
		mv.addObject("size", size);
		mv.addObject("PageName","Notice Board");
	
		mv.addObject("dto", boardVO);
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdate2(BoardVO boardVO, MultipartFile [] file, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardNoticeService.boardUpdate(boardVO, file, session);
		if(result>0) {
			mv.setViewName("redirect:./noticeList");
		}else {
			mv.addObject("path", "./noticeList");
			mv.addObject("msg", "Update Fail");
			mv.setViewName("common/common_result");
		}
		return mv;
		
	}
	
	@RequestMapping(value = "noticeSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(BoardVO boardVO) throws Exception{
		boardVO = boardNoticeService.boardSelect(boardVO);
		ModelAndView mv = new ModelAndView();
		boardVO.setContents(boardVO.getContents().replace("\r\n", "<br>"));
		//mv.addObject("dto", boardVO);
		mv.addObject("PageName","Notice Board");
		
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@RequestMapping(value = "noticeList", method = RequestMethod.GET)
	public ModelAndView boardList(Pager pager)throws Exception{
		List<BoardVO> ar = boardNoticeService.boardList(pager);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.addObject("PageName","Notice Board");
		
//		BoardVO boardVO = new BoardQnaVO();
//		BoardQnaVO boardQnaVO = (BoardQnaVO)boardVO;
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping(value = "noticeWrite", method = RequestMethod.GET)
	public ModelAndView boardWrite()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("PageName","Notice Board");
		
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@RequestMapping(value = "noticeWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardVO boardVO, MultipartFile [] file, HttpSession session)throws Exception{
//		for(int i=0; i<file.length; i++) {
//			System.out.println(file[i].getOriginalFilename());
//		}
		int result = boardNoticeService.boardWrite(boardVO, file, session);
		ModelAndView mv = new ModelAndView();
		if(result>0) {
			mv.setViewName("redirect:./noticeList");
		}else {
			mv.addObject("msg", "Write Fail");
			mv.addObject("path", "./noticeList");
			mv.setViewName("common/common_result");
		}
		return mv;
		
	}
	
	
	/*
	 * @ExceptionHandler(NullPointerException.class) public ModelAndView getNull() {
	 * ModelAndView mv = new ModelAndView();
	 * mv.setViewName("common/common_500_error"); return mv; }
	 */
	
}
