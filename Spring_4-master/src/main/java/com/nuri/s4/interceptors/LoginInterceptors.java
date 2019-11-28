package com.nuri.s4.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptors extends HandlerInterceptorAdapter{

	
	//Controller 종료 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	//Controller 진입 전 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//retrun이 true라면 Controller로 전송
		//false : Controller 전송 X
		boolean result = false;
		Object obj = request.getSession().getAttribute("member");
		
		if(obj != null) {
			result = true;
		}else {
			response.sendRedirect("../member/memberLogin");
		}
		
		return result;
	}
	
}
