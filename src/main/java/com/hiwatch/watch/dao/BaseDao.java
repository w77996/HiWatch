package com.hiwatch.watch.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.sun.tools.javac.util.List;

public interface BaseDao<T> {

	int insert(T entity);
	
	int insert(List<T> list);
	
	int update(T entity);
	
	int update(List<T> list);
	
	int update(Map<String, Object> param);
	
	T getById(int id);
	
	public T getByColum(Map<String, Object> param);
	
	public T getBy(Map<String, Object> paramMap);
	
	public List<T> listBy(Map<String, Object> param);
	
	public List<T> listByColum(Map<String, Object> param);
	
	Long getCountByColum(Map<String, Object> param);
	
	int delete(int id);
	
	int delete(List<T> list);
	
	int delete(Map<String, Object> param);
	
	public SqlSession getSqlSession();
	
	public SqlSessionTemplate geSqlSessionTemplate();

}
