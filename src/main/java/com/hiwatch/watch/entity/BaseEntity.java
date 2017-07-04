package com.hiwatch.watch.entity;

import java.util.Date;



/**
 * Description: 实体类的基类
 * @author w77996
 * @date 2017年7月4日 下午2:08:11
 */
public class BaseEntity {
	
	private Date editTime;//修改时间

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date date) {
		this.editTime = date;
	}
	
	
}
