package com.hiwatch.watch.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hiwatch.watch.dao.DeviceMapper;
import com.hiwatch.watch.entity.Userdevice;
/**
 * 设备daoimpl
 * Description:
 * @author w77996
 * @date 2017年7月11日 下午7:15:26
 */
@Repository
public class DeviceDaoImpl extends BaseDaoImpl<Userdevice> implements DeviceMapper {

	@Override
	public int queryUserOrDevice(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.getSqlSessionTemplate().selectOne(getStatement("queryUserOrDevice"),param);
	}

	

}
