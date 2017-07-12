package com.hiwatch.watch.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * app版本
 * Description:
 * @author w77996
 * @date 2017年7月12日 下午4:39:50
 */
public class Versioninfo extends BaseEntity implements Serializable{
    
	private static final long serialVersionUID = -5116057731359506261L;

	private Integer versionId;

    private String appVersion;

    private String downLoad;

    private Date createTime;

    private String funcationCap;

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getDownLoad() {
        return downLoad;
    }

    public void setDownLoad(String downLoad) {
        this.downLoad = downLoad == null ? null : downLoad.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFuncationCap() {
        return funcationCap;
    }

    public void setFuncationCap(String funcationCap) {
        this.funcationCap = funcationCap == null ? null : funcationCap.trim();
    }
}