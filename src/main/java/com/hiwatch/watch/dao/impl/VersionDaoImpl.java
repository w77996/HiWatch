package com.hiwatch.watch.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hiwatch.watch.dao.VersionMapper;
import com.hiwatch.watch.entity.Versioninfo;
@Repository
public class VersionDaoImpl extends BaseDaoImpl<Versioninfo> implements VersionMapper{

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public Versioninfo queryVersion() {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectOne(getStatement("queryVersion"));
	}
	
	
}
