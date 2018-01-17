package com.wanghao.controller;

import java.util.Date;

public class SysUser {
    private Integer id;

    private String loginid;

    private String pwd;

    private String username;

    private String email;

    private Integer sex;

    private String telno;

    private Integer isdel;

    private Date createdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelno() {
        return telno;
    }

    public void setTelno(String telno) {
        this.telno = telno;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginid='" + loginid + '\'' +
                ", pwd='" + pwd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", telno='" + telno + '\'' +
                ", isdel=" + isdel +
                ", createdate=" + createdate +
                '}';
    }
}