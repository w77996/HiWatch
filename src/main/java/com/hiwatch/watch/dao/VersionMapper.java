package com.hiwatch.watch.dao;

import com.hiwatch.watch.entity.Versioninfo;

public interface VersionMapper extends BaseDao<Versioninfo>{

	Versioninfo queryVersion();
}
