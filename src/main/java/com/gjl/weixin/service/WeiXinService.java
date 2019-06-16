package com.gjl.weixin.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

public interface WeiXinService {
    boolean menuAdd();

    public  String processRequest(HttpServletRequest request) ;
}
