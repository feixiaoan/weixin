package com.gjl.weixin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/30 14:28
 * @Version 1.0
 */
public class ComplainDto {

    private String userName;

    @JsonFormat(pattern="yyyy-MM-dd ",timezone = "GMT+8")
    private String complainTime;

    private String complainSubject;

    private String complainReason;

    private String complainVideo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        this.complainSubject = complainSubject;
    }

    public String getComplainReason() {
        return complainReason;
    }

    public void setComplainReason(String complainReason) {
        this.complainReason = complainReason;
    }

    public String getComplainVideo() {
        return complainVideo;
    }

    public void setComplainVideo(String complainVideo) {
        this.complainVideo = complainVideo;
    }
}
