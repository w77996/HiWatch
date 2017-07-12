package com.hiwatch.watch.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
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
		System.out.println("fsadfasfdasdfsadfasdfsa");
		SqlSessionTemplate sessionTemplate = this.getSqlSessionTemplate();
		if(sessionTemplate !=null){
			System.out.println("no null");
		}
		System.out.println(getStatement("queryUserOrDevice"));
		int num = sessionTemplate.selectOne(getStatement("queryUserOrDevice"),param);
		System.out.println(num+"6666666666");
		return num;
	}

	

}
