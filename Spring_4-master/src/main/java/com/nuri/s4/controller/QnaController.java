package com.nuri.s4.controller;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nuri.s4.model.BoardQnaVO;
import com.nuri.s4.model.BoardVO;
import com.nuri.s4.model.FilesVO;
import com.nuri.s4.service.BoardQnaService;
import com.nuri.s4.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Inject
	private BoardQnaService boardQnaService;
	
	@GetMapping(value = "fileDown2")
	public ModelAndView fileDown(FilesVO filesVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		filesVO = boardQnaService.fileSelect(filesVO);
		mv.addObject("file", filesVO);
		mv.addObject("board", "qna");
		mv.setViewName("fileDown2");
		return mv;
	}

	
	@PostMapping(value = "fileDelete")
	public ModelAndView fileDelete(FilesVO filesVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardQnaService.fileDelete(filesVO);
		mv.setViewName("common/common_ajaxResult");
		mv.addObject("result", result);
		
		return mv;
	}
	
	
	@RequestMapping(value = "qnaReply", method = RequestMethod.GET)
	public ModelAndView boardReply(BoardVO boardVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("dto", boardVO);
		mv.setViewName("board/boardReply");
		return mv; 
	}
	
	@RequestMapping(value = "qnaReply", method = RequestMethod.POST)
	public ModelAndView boardReply2(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardQnaService.boardReply(boardVO);
		if(result>0) {
			mv.setViewName("redirect:./qnaList");
		}else {
			mv.addObject("msg", "Fail");
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	@RequestMapping(value = "qnaDelete", method = RequestMethod.GET)
	public ModelAndView boardDelete(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = boardQnaService.boardDelete(boardVO);
		if(result>0) {
			mv.setViewName("redirect:./qnaList");
		}else {
			mv.addObject("msg", "Delete Fail");
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.GET)
	public ModelAndView boardUpdate(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = boardQnaService.boardSelect(boardVO);
		BoardQnaVO qnaVO = (BoardQnaVO)boardVO;
		int size = qnaVO.getFiles().size();
		mv.addObject("size", size);
		mv.addObject("dto", boardVO);
		mv.addObject("PageName", "QnA Board");
		mv.addObject("board", "qna");
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	
	@RequestMapping(value = "qnaUpdate", method = RequestMethod.POST)
	public ModelAndView boardUpdate2(BoardVO boardVO, MultipartFile [] file, HttpSession session)throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = boardQnaService.boardUpdate(boardVO, file, session);
		if(result>0) {
			mv.setViewName("redirect:./qnaList");
		}else {
			mv.addObject("msg", "Write Fail");
			mv.addObject("path", "./qnaList");
			mv.setViewName("common/common_result");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "qnaSelect", method = RequestMethod.GET)
	public ModelAndView boardSelect(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = boardQnaService.boardSelect(boardVO);
		mv.addObject("PageName", "QnA Board");
		mv.addObject("board", "qna");
		mv.addObject("dto", boardVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@RequestMapping(value = "qnaList", method = RequestMethod.GET)
	public ModelAndView boardList(Pager pager)throws Exception{
		List<BoardVO> ar = boardQnaService.boardList(pager);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.addObject("PageName", "QnA Board");
		mv.addObject("board", "qna");
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping(value = "qnaWrite", method = RequestMethod.GET)
	public ModelAndView boardWrite()throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("PageName","QnA Board");
		mv.addObject("board", "qna");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	@RequestMapping(value = "qnaWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardVO boardVO, MultipartFile [] file, HttpSession session)throws Exception{
		int result = boardQnaService.boardWrite(boardVO, file, session);
		ModelAndView mv = new ModelAndView();
		if(result>0) {
			mv.setViewName("redirect:./qnaList");
		}else{
			mv.addObject("msg", "QnA Write Fail");
			mv.addObject("path", "qnaList");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	
	
}