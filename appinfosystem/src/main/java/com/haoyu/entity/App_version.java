package com.haoyu.entity;

import java.math.BigDecimal;
import java.util.Date;

public class App_version {
    private Long id;

    private Long appid;

    private String versionno;

    private String versioninfo;

    private Long publishstatus;

    private String downloadlink;

    private BigDecimal versionsize;

    private Long createdby;

    private Date creationdate;

    private Long modifyby;

    private Date modifydate;

    private String apklocpath;

    @Override
    public String toString() {
        return "App_version{" +
                "id=" + id +
                ", appid=" + appid +
                ", versionno='" + versionno + '\'' +
                ", versioninfo='" + versioninfo + '\'' +
                ", publishstatus=" + publishstatus +
                ", downloadlink='" + downloadlink + '\'' +
                ", versionsize=" + versionsize +
                ", createdby=" + createdby +
                ", creationdate=" + creationdate +
                ", modifyby=" + modifyby +
                ", modifydate=" + modifydate +
                ", apklocpath='" + apklocpath + '\'' +
                ", apkfilename='" + apkfilename + '\'' +
                ", softwarename='" + softwarename + '\'' +
                ", publishstatusname='" + publishstatusname + '\'' +
                '}';
    }

    private String apkfilename;

    public String getSoftwarename() {
        return softwarename;
    }

    public void setSoftwarename(String softwarename) {
        this.softwarename = softwarename;
    }

    public String getPublishstatusname() {
        return publishstatusname;
    }

    public void setPublishstatusname(String publishstatusname) {
        this.publishstatusname = publishstatusname;
    }

    //app名字
    private String softwarename;
    //发布状态
    private String publishstatusname;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno == null ? null : versionno.trim();
    }

    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo == null ? null : versioninfo.trim();
    }

    public Long getPublishstatus() {
        return publishstatus;
    }

    public void setPublishstatus(Long publishstatus) {
        this.publishstatus = publishstatus;
    }

    public String getDownloadlink() {
        return downloadlink;
    }

    public void setDownloadlink(String downloadlink) {
        this.downloadlink = downloadlink == null ? null : downloadlink.trim();
    }

    public BigDecimal getVersionsize() {
        return versionsize;
    }

    public void setVersionsize(BigDecimal versionsize) {
        this.versionsize = versionsize;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getApklocpath() {
        return apklocpath;
    }

    public void setApklocpath(String apklocpath) {
        this.apklocpath = apklocpath == null ? null : apklocpath.trim();
    }

    public String getApkfilename() {
        return apkfilename;
    }

    public void setApkfilename(String apkfilename) {
        this.apkfilename = apkfilename == null ? null : apkfilename.trim();
    }
}