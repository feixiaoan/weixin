package com.gjl.weixin.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Plate {

    @Value("${plate.url}")
    private String plateUrl;
    @Value("${plate.num}")
    private String plateNum;
    @Value("${plate.password}")
    private String platePassword;

    public String getPlateUrl() {
        return plateUrl;
    }

    public void setPlateUrl(String plateUrl) {
        this.plateUrl = plateUrl;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getPlatePassword() {
        return platePassword;
    }

    public void setPlatePassword(String platePassword) {
        this.platePassword = platePassword;
    }
}
