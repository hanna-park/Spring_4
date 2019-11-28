package com.nuri.s4.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	// File Delete
	public boolean fileDelete(String realPath, String fileName) throws Exception{
		File file = new File(realPath, fileName);
		
		boolean check = false;
		if(file.exists()) {
			check = file.delete();
		}
		return check;
	}
	
	// 3. Io Stream 사용
	public String save3(String realPath, MultipartFile multipartFile)throws Exception{
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+multipartFile.getOriginalFilename();
		
		file = new File(realPath, fileName);
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		
		System.out.println(realPath);
		return fileName;
	}
	
	
	// 2. MultipartFile transferTo 메서드 사용
	public String save2(String realPath, MultipartFile multipartFile) throws Exception{
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		// 저장할 파일명 두번째 방법 :UUID(universal Unique IDentify)  
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+multipartFile.getOriginalFilename();
		
		file = new File(realPath, fileName);
		multipartFile.transferTo(file);
		
		System.out.println(realPath);
		return fileName;
		
	}
	
	// 1. Spring에서 제공하는 FileCopyUtils 클래스의 copy메서드 사용
	public String save(String realPath, MultipartFile multipartFile) throws Exception{
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		Calendar ca = Calendar.getInstance();
		Long name = ca.getTimeInMillis();
		int r = multipartFile.getOriginalFilename().lastIndexOf(".");
		String fileName = name+multipartFile.getOriginalFilename().substring(r);
		
		file = new File(realPath, String.valueOf(fileName));
		FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		System.out.println(realPath);
		return fileName;
	}
}
