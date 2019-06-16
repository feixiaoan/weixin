package com.gjl.weixin.entity;

import java.util.List;

public class WeixinUser {

    private List<String> openid;
    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }
    public List<String> getOpenid() {
        return openid;
    }

    @Override
    public String toString() {
        return "WeixinUser{" +
                "openid=" + openid +
                '}';
    }
}
