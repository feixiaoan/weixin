package com.gjl.weixin.entity;

public class SysParam {
    private Integer id;

    private String syscode;

    private String sysname;

    private String sysvalue;

    private String enabled;

    private String systype;

    private String sysdetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode == null ? null : syscode.trim();
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname == null ? null : sysname.trim();
    }

    public String getSysvalue() {
        return sysvalue;
    }

    public void setSysvalue(String sysvalue) {
        this.sysvalue = sysvalue == null ? null : sysvalue.trim();
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled == null ? null : enabled.trim();
    }

    public String getSystype() {
        return systype;
    }

    public void setSystype(String systype) {
        this.systype = systype == null ? null : systype.trim();
    }

    public String getSysdetails() {
        return sysdetails;
    }

    public void setSysdetails(String sysdetails) {
        this.sysdetails = sysdetails == null ? null : sysdetails.trim();
    }
}