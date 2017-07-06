package com.hiwatch.watch.entity;

import java.io.Serializable;
import java.util.Date;

public class Verfiycode extends BaseEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5807118263733158963L;

	private Integer verfiyId;

    private String userAccount;

    private String code;

    private String type;

    private Integer status;

    private Date createTime;

    public Integer getVerfiyId() {
        return verfiyId;
    }

    public void setVerfiyId(Integer verfiyId) {
        this.verfiyId = verfiyId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}