package com.hiwatch.watch.dao.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hiwatch.watch.dao.OpintionMapper;
import com.hiwatch.watch.entity.Opoinioninfo;
@Repository
public class OpintionDaoImpl extends BaseDaoImpl<Opoinioninfo> implements OpintionMapper {

	
	@Autowired
	private OpintionMapper opintionMapper;
}
