package com.nuri.s4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.nuri.s4.model.FilesVO;

@Component
public class FileDown extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		FilesVO filesVO = (FilesVO)model.get("file");
		String board = (String)model.get("board");
//		System.out.println(noticeFilesVO.getFname());
//		System.out.println(noticeFilesVO.getOname());
//		System.out.println(board);
		
		String realPath = request.getSession().getServletContext().getRealPath("resources/upload/"+board);
		System.out.println(realPath);
		File file = new File(realPath, filesVO.getFname());
		// 한글처리
		response.setCharacterEncoding("UTF-8");
		
		// 파일의 크기
		response.setContentLength((int)file.length());
		
		// 다운로드시 파일 이름 인코딩
		String fileName = URLEncoder.encode(filesVO.getOname(), "UTF-8");
		
		// header 설정
		response.setHeader("Content-disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-transfer-Encoding", "binary");
		
		// outputStream으로 보내기
		FileInputStream fi = new FileInputStream(file); // 어디에 있는 객체를 읽어와서
		OutputStream os = response.getOutputStream(); // 보내줄지?
		
		FileCopyUtils.copy(fi, os);
		
		// 자원 해제하기
		os.close();
		fi.close();
		
		
	}
	
}
