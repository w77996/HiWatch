package com.hiwatch.watch.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.hiwatch.watch.dao.BaseDao;
import com.hiwatch.watch.entity.BaseEntity;
import com.hiwatch.watch.exception.BaseException;


/**
 * Description:DaoImpl基类
 * @author w77996
 * @date 2017年7月4日 下午1:59:30
 * @param <T>
 */
public abstract class BaseDaoImpl<T extends BaseEntity> extends SqlSessionDaoSupport implements BaseDao<T>  {
	
	protected static final Log LOG = LogFactory.getLog(BaseDaoImpl.class);
	
	public static final String SQL_INSERT = "insertSelective";//单挑插入数据sql
	public static final String SQL_BATCH_INSERT ="batchInsert";//批量插入数据sql
	public static final String SQL_UPDATE_BY_ID = "updateByPrimaryKey";//根据id更新数据
	public static final String SQL_SELECT_BY_ID = "selectByPrimaryKey";//根据id查找数据
	public static final String SQL_DELETE_BY_ID = "deleteByPrimaryKey";//根据id删除数据
	public static final String SQL_LIST_BY = "listBy";
	
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public SqlSession getSqlSession(){
		return super.getSqlSession();
	}
	/**
	 * 
	 * Title: insert
	 * Description: 单条插入数据到数据库
	 * @param entity
	 * @return
	 * @see com.hiwatch.watch.dao.BaseDao#insert(java.lang.Object)
	 */
	public int insert(T entity){
		int result = sqlSessionTemplate.insert(getStatement(SQL_INSERT), entity);
		
		if(result <= 0 ){
			System.out.println("insert 返回0");
			throw  BaseException.DB_INSERT_RESULT_0.newInstence("insert 返回0 ", getStatement(SQL_INSERT));
		}
		return result;
	}
	/**
	 * 批量插入数据到数据库
	 * Title: insert
	 * Description: 
	 * @param list
	 * @return
	 * @see com.hiwatch.watch.dao.BaseDao#insert(com.sun.tools.javac.util.List)
	 */
	/*public int insert(List<T> list){
		if(list.size() <= 0 || list ==null){
			return 0;
		}
		int result =  sqlSessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
		if(result <= 0){
			throw BaseException.DB_LIST_IS_NULL.newInstence("数据库操作，批量插入返回0%{s}", getStatement(SQL_BATCH_INSERT));
		}
		return result;
	}*/
	/**
	 * 按照id,单条更新数据
	 * Title: update
	 * Description: 
	 * @param entity
	 * @return
	 * @see com.hiwatch.watch.dao.BaseDao#update(java.lang.Object)
	 */
	public int update(T entity){
		entity.setEditTime(new java.util.Date());
		int result = sqlSessionTemplate.update(getStatement(SQL_UPDATE_BY_ID), entity);
		if(result <= 0){
			throw BaseException.DB_UPDATE_RESULT_0.newInstence("数据库操作，更新返回0%{s}", getStatement(SQL_UPDATE_BY_ID));
		}
		return result;
	}
	/*public int update(List<T> list){
		if(list.size() < 0 || list == null){
			return 0;
		}
		int result = sqlSessionTemplate.insert(getStatement(sqlId), parameter)
	}*/
	/**
	 * 根据ID查找数据
	 * Title: getById
	 * Description: 
	 * @param id
	 * @return
	 * @see com.hiwatch.watch.dao.BaseDao#getById(int)
	 */
	/*public T getById(int id){
		return sqlSessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID),id);
	}*/
	
	public T getBy(Map<String, Object> paramMap){
		if(paramMap == null){
			return null;
		}
		return sqlSessionTemplate.selectOne(getStatement(SQL_LIST_BY), paramMap);
	}
	/**
	 * 根据ID删除数据
	 * Title: delete
	 * Description: 
	 * @param id
	 * @return
	 * @see com.hiwatch.watch.dao.BaseDao#delete(int)
	 */
	/*public int delete(int id){
		return sqlSessionTemplate.delete(getStatement(SQL_DELETE_BY_ID),id);
	}*/
	/**
	 * 获取命名空间 
	 * @Title:           getStatement
	 * @Description:     TODO
	 * @param:           @param sqlId
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	public String getStatement(String sqlId){
		String nameString = this.getClass().getName();
		//单线程使用StringBulder,确保速度；多线程用StringBuffer，确保安全
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(nameString).append(".").append(sqlId);
		return sBuilder.toString();
	}
}
