package com.hiwatch.watch.entity;

import java.io.Serializable;
import java.util.Date;

public class Opoinioninfo extends BaseEntity implements Serializable{
    
	private static final long serialVersionUID = -7174419815991580546L;

	private Integer opinionId;

    private Integer userId;

    private String contact;

    private Date createTime;

    private String opinionContent;

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent == null ? null : opinionContent.trim();
    }
}