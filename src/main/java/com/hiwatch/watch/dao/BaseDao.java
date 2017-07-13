package com.hiwatch.watch.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

/**
 * 
 * Description:Dao基类，编写数据库操作接口
 * @author w77996
 * @date 2017年7月5日 下午6:23:58
 * @param <T>
 */
public interface BaseDao<T> {

	int insert(T entity);
	
	int insert(List<T> list);
	
	int update(T entity);
	
	//int update(List<T> list);
	
	//int update(Map<String, Object> param);
	
	T getById(int id);
	
	//public T getByColum(Map<String, Object> param);
	
	public T getBy(Map<String, Object> paramMap);
	
	public List<T> listBy(Map<String, Object> param);
	
	//public List<T> listByColum(Map<String, Object> param);
	
	//Long getCountByColum(Map<String, Object> param);
	
	int delete(int id);
	
	/*int delete(List<T> list);
	
	int delete(Map<String, Object> param);*/
	
	//public int insertByBatch(List<T> list);
	
	public SqlSession getSqlSession();
	
	public SqlSessionTemplate getSqlSessionTemplate();

}
