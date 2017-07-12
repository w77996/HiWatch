package com.hiwatch.watch.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiwatch.watch.dao.VersionMapper;
import com.hiwatch.watch.entity.Versioninfo;
import com.hiwatch.watch.service.VersionService;
@Service
public class VersionServiceImpl implements VersionService{

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public Versioninfo queryVersioninfo() {
		// TODO Auto-generated method stub
		return versionMapper.queryVersion();
	}
	
	
}
