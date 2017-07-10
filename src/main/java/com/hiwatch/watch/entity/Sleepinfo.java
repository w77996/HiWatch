package com.hiwatch.watch.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 睡眠信息实体类
 * Description:
 * @author w77996
 * @date 2017年7月10日 下午4:57:34
 */
public class Sleepinfo extends BaseEntity implements Serializable{
   
	private static final long serialVersionUID = -4988076212185039802L;

	private Integer sleepId;

    private Integer userId;

    private Date sleepStart;

    private Date sleepEnd;

    private Integer sleepStatus;

    private Date createTime;

    public Integer getSleepId() {
        return sleepId;
    }

    public void setSleepId(Integer sleepId) {
        this.sleepId = sleepId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSleepStart() {
        return sleepStart;
    }

    public void setSleepStart(Date sleepStart) {
        this.sleepStart = sleepStart;
    }

    public Date getSleepEnd() {
        return sleepEnd;
    }

    public void setSleepEnd(Date sleepEnd) {
        this.sleepEnd = sleepEnd;
    }

    public Integer getSleepStatus() {
        return sleepStatus;
    }

    public void setSleepStatus(Integer sleepStatus) {
        this.sleepStatus = sleepStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}