package com.nuri.s4.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.nuri.s4.model.FilesVO;

@Repository
public class NoticeFilesDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="noticeFilesMapper.";
	
	public FilesVO fileSelect(FilesVO filesVO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"fileSelect", filesVO);
	}
	
	public int fileUpdate(FilesVO filesVO)throws Exception{
		return sqlSession.update(NAMESPACE+"fileUpdate", filesVO);
	}
	
	public int fileDelete(FilesVO filesVO)throws Exception{
		return sqlSession.delete(NAMESPACE+"fileDelete", filesVO);
	}

	public int fileWrite(FilesVO filesVO) throws Exception{
		 return sqlSession.insert(NAMESPACE+"fileWrite", filesVO);
	}
	
	public List<FilesVO> fileList(int num)throws Exception{
		return sqlSession.selectList(NAMESPACE+"fileList", num);
	}
}
