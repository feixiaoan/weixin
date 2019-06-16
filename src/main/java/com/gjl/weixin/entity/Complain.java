package com.gjl.weixin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Complain {
    private Integer id;

    private String userId;

    @JsonFormat(pattern="yyyy-MM-dd ",timezone = "GMT+8")
    private String complainTime;

    private String complainSubject;

    private String complainReason;

    private String complainVideo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComplainTime() {
        return complainTime;
    }

    public void setComplainTime(String complainTime) {
        this.complainTime = complainTime;
    }

    public String getComplainSubject() {
        return complainSubject;
    }

    public void setComplainSubject(String complainSubject) {
        this.complainSubject = complainSubject == null ? null : complainSubject.trim();
    }

    public String getComplainReason() {
        return complainReason;
    }

    public void setComplainReason(String complainReason) {
        this.complainReason = complainReason == null ? null : complainReason.trim();
    }

    public String getComplainVideo() {
        return complainVideo;
    }

    public void setComplainVideo(String complainVideo) {
        this.complainVideo = complainVideo == null ? null : complainVideo.trim();
    }
}