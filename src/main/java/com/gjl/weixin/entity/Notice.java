package com.gjl.weixin.entity;

import java.util.Date;

public class Notice {
    private Integer id;

    private String noticeDate;

    private String noticeContent;

    private String noticeSpare;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }

    public String getNoticeSpare() {
        return noticeSpare;
    }

    public void setNoticeSpare(String noticeSpare) {
        this.noticeSpare = noticeSpare == null ? null : noticeSpare.trim();
    }
}