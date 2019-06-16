package com.gjl.weixin.controller;

import com.gjl.weixin.entity.AccessToken;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenThread {

    private static Logger logger = Logger.getLogger(AccessTokenThread.class);

    public static AccessToken accessToken = new AccessToken();

    @Scheduled(fixedDelay = 2*3600*1000)
    public void getToken(){

        String getToken= WeChatController.getAccessToken();
        if(null!= accessToken){
            accessToken.setAccess_token(getToken);
            System.out.println("执行完毕");
            logger.info("获取成功，accessToken:"+getToken);
        }else{
            logger.error("获取token失败");
        }

    }

}
