package com.nuri.s4;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;

public class Test extends TestAbstractCase {
	
	@Inject
	private DataSource dataSource;
	@Inject
	private SqlSession sqlSession;
	
	@org.junit.Test
	public void sqlSessionTest() throws Exception{
		assertNotNull(sqlSession);
	}
}
