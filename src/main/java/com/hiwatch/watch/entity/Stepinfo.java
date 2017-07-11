package com.hiwatch.watch.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 步数实体类
 * Description:
 * @author w77996
 * @date 2017年7月11日 上午9:24:32
 */
public class Stepinfo extends BaseEntity implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 602831537285745678L;

	private Integer stepId;

    private Integer userId;

    private Integer stepNum;

    private Float mileage;

    private Integer calorie;

    private Date createTime;

    private Integer timeKey;

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public Float getMileage() {
        return mileage;
    }

    public void setMileage(Float mileage) {
        this.mileage = mileage;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTimeKey() {
        return timeKey;
    }

    public void setTimeKey(Integer timeKey) {
        this.timeKey = timeKey;
    }
}