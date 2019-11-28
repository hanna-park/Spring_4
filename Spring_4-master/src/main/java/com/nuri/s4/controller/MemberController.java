package com.nuri.s4.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nuri.s4.model.MemberVO;
import com.nuri.s4.service.MemberServiceImpl;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Inject
	private MemberServiceImpl memberServiceImpl;
	
	
	@GetMapping(value = "memberDelete")
	public ModelAndView memberDelete(MemberVO memberVO, HttpSession httpSession) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberServiceImpl.memberDelete(memberVO);
		if(result>0) {
			httpSession.removeAttribute("member");
			mv.addObject("msg", "Delete Success");
			mv.addObject("path", "../");
			mv.setViewName("common/common_result");
			
		}else {
			mv.addObject("msg", "Delete Fail");
			mv.addObject("path", "./memberMyPage");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	@PostMapping(value = "memberUpdate")
	public ModelAndView memberUpdate(MemberVO memberVO, HttpSession httpSession)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberServiceImpl.memberUpdate(memberVO);
		if(result>0) {
			mv.setViewName("redirect:./memberMypage");
			httpSession.setAttribute("member", memberVO);
		}else {
			mv.addObject("msg", "Update Fail");
			mv.addObject("path", "./memberMypage");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	@GetMapping(value = "memberUpdate")
	public void memberUpdate() throws Exception {
	}
	
	@GetMapping(value = "memberMypage")
	public void memberMypage() throws Exception {
	}
	
	@GetMapping(value = "memberLogout")
	public String memberLogout(HttpSession httpSession) throws Exception{
		// 1번째 : session의 속성을 없애는 방법
		httpSession.removeAttribute("member");
		
		// 2번쩨 : session의 시간을 초기화 즉, 0으로 만드는 방법.
		// httpSession.invalidate();
		return "redirect:../";
	}
	
	@GetMapping(value = "memberLogin")
	public void memberLogin() throws Exception{
	}	
	
	@PostMapping(value = "memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO, HttpSession httpSession) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberServiceImpl.memberLogin(memberVO);
		if(memberVO != null) {
			httpSession.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}else {
			mv.addObject("msg", "Login Fail");
			mv.addObject("path", "../");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	@PostMapping(value = "memberIdCheck")
	public void memberIdCheck(MemberVO memberVO, Model model) throws Exception{
		memberVO = memberServiceImpl.memberIdCheck(memberVO);
		
		String result = "unpass";
		
		if(memberVO == null) {
			// 사용가능
			result = "pass";
		}
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "memberJoin", method = RequestMethod.GET)
	public ModelAndView memberJoin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/memberJoin");
		return mv;
		
	}
	
	@RequestMapping(value = "memberJoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(MemberVO memberVO, HttpSession session, HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		int result = memberServiceImpl.memberJoin(memberVO, session);
		if(result>0) {
			mv.setViewName("redirect:../");
		}else {
			mv.addObject("msg", "Join Fail");
			mv.addObject("path", "../");
			mv.setViewName("common/common_result");
		}
		return mv;
	}
	
	
}
