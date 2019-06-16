package com.gjl.weixin.service.impl;

import com.gjl.weixin.controller.AccessTokenThread;
import com.gjl.weixin.entity.Article;
import com.gjl.weixin.entity.NewsMessage;
import com.gjl.weixin.entity.TextMessage;
import com.gjl.weixin.service.WeiXinService;
import com.gjl.weixin.utils.HttpUtil;
import com.gjl.weixin.utils.MsgUtil;
import com.gjl.weixin.utils.WeiXinUtil;
import com.gjl.weixin.utils.WeixinMessageUtil;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class WeixinServiceImpl implements WeiXinService {

    private static Logger logger = Logger.getLogger(WeixinServiceImpl.class);
    /**
     * 添加自定义菜单
     */
    @Override
    public boolean menuAdd(){


        //String url = WeiXinUtil.ADD_MENU_URL.replace("ACCESS_TOKEN", WeiXinUtil.getAccessToken());
        String url = WeiXinUtil.ADD_MENU_URL.replace("ACCESS_TOKEN", AccessTokenThread.accessToken.getAccess_token());
        String menu = JSONObject.fromObject(MsgUtil.initMenu()).toString();
        JSONObject result = HttpUtil.doPoststr(url,menu);
        if("ok".equals(result.getString("errmsg"))){
            //logger.info("添加菜单结果：{}", result);
            System.out.println("true add menu result：{}"+result);
            return true;
        }
        //logger.info("添加菜单结果：{}", result);
        System.out.println("falseadd menu result：{}"+result);
        return false;
    }

    @Override
    public String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try{
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";
            // xml请求解析
            Map<String, String> requestMap = WeixinMessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(WeixinMessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            // 创建图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setToUserName(fromUserName);
            newsMessage.setFromUserName(toUserName);
            newsMessage.setCreateTime(new Date().getTime());
            newsMessage.setMsgType(WeixinMessageUtil.RESP_MESSAGE_TYPE_NEWS);
            newsMessage.setFuncFlag(0);
            List<Article> articleList = new ArrayList<Article>();
            // 接收文本消息内容
            String content = requestMap.get("Content");
            // 自动回复文本消息
           if(msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                //如果用户发送表情，则回复同样表情。
               if(isQqFace(content)){
                   respContent = content;
                   textMessage.setContent(respContent);
                   respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
               }
               else{
                   switch (content){
                       case "1":{
                           StringBuffer buffer = new StringBuffer();
                           buffer.append("您好，我是小宝，请回复数字选择服务：").append("\n\n");
                           buffer.append("11  问卷调查步骤").append("\n");
                           buffer.append("22  投诉步骤").append("\n");
                           buffer.append("33  百度访问链接").append("\n");
                           buffer.append("还可发送表情试试哦！").append("\n");
                           buffer.append("回复“1”显示此帮助菜单").append("\n");
                           respContent = String.valueOf(buffer);
                           textMessage.setContent(respContent);
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                           break;
                       }
                       case "22":{
                           /*Article article = new Article();
                           article.setTitle("微信公众帐号开发教程Java版");
                           article.setDescription("这是测试有没有换行\n\n如果有空行就代表换行成功\n\n点击图文可以跳转到百度首页");
                           // 将图片置为空
                           article.setPicUrl("http://www.sinaimg.cn/dy/slidenews/31_img/2016_38/28380_733695_698372.jpg");
                           article.setUrl("http://www.baidu.com");
                           articleList.add(article);
                           newsMessage.setArticleCount(articleList.size());
                           newsMessage.setArticles(articleList);
                           respMessage = WeixinMessageUtil.newsMessageToXml(newsMessage);
                           break;*/
                           StringBuffer buffer = new StringBuffer();
                           buffer.append("投诉").append("\n\n");
                           buffer.append("1. 点击功能列表菜单").append("\n");
                           buffer.append("2. 点击菜单里面“投诉”按钮").append("\n");
                           buffer.append("3. 根据提示登录后即可进行投诉").append("\n");
                           buffer.append("4. 用户名，密码均有您所在企业提供，如不清楚请联系自己的老师").append("\n");
                           buffer.append("回复“1”显示此帮助菜单").append("\n");
                           respContent = String.valueOf(buffer);
                           textMessage.setContent(respContent);
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                           break;
                       }
                       case "11":{
                           StringBuffer buffer = new StringBuffer();
                           buffer.append("问卷调查").append("\n\n");
                           buffer.append("1. 点击功能列表菜单").append("\n");
                           buffer.append("2. 点击菜单里面“调查问卷”按钮").append("\n");
                           buffer.append("3. 根据提示登录后即可参与问卷评价").append("\n");
                           buffer.append("4. 用户名，密码均有您所在企业提供，如不清楚请联系自己的老师").append("\n");
                           buffer.append("回复“1”显示此帮助菜单").append("\n");
                           respContent = String.valueOf(buffer);
                           textMessage.setContent(respContent);
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                           break;
                       }
                       case "33": {
                           //测试网址回复
                           respContent = "<a href=\"http://www.baidu.com\">百度主页</a>";
                           textMessage.setContent(respContent);
                           // 将文本消息对象转换成xml字符串
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                           break;
                       } default: {
                           respContent = "非常抱歉，现在“小宝”暂时无法为您解答。\n\n回复“1”显示帮助信息";
                           textMessage.setContent(respContent);
                           // 将文本消息对象转换成xml字符串
                           respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
                       }
                   }
               }
           }else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
               respContent = "您发送图片消息真是美极啦！";
               textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
           // 地理位置消息
            else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送地理位置真是宝地呀！";
                textMessage.setContent(respContent);
                // 将文本消息对象转换成xml字符串
                respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
            }
           // 链接消息
           else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_LINK)) {
               respContent = "您发送的是链接消息，小宝会留意的！";textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
           // 音频消息
           else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
               respContent = "您的音频消息！小宝听见啦，您的声音真好听！";
               textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
           else if (msgType.equals(WeixinMessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
               respContent = "您的视频消息！小宝看见啦，里面的故事真好看！";
               textMessage.setContent(respContent);
               // 将文本消息对象转换成xml字符串
               respMessage = WeixinMessageUtil.textMessageToXml(textMessage);
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return respMessage;
    }

    public static boolean isQqFace(String source){
        if(source.contains("::")){
            return true;
        }
        int len = source.length();
        boolean isEmoji = false;
        for (int i = 0; i < len; i++) {
            char hs = source.charAt(i);
            if (0xd800 <= hs && hs <= 0xdbff) {
                if (source.length() > 1) {
                    char ls = source.charAt(i + 1);
                    int uc = ((hs - 0xd800) * 0x400) + (ls - 0xdc00) + 0x10000;
                    if (0x1d000 <= uc && uc <= 0x1f77f) {
                        return true;
                    }
                }
            } else {
                // non surrogate
                if (0x2100 <= hs && hs <= 0x27ff && hs != 0x263b) {
                    return true;
                } else if (0x2B05 <= hs && hs <= 0x2b07) {
                    return true;
                } else if (0x2934 <= hs && hs <= 0x2935) {
                    return true;
                } else if (0x3297 <= hs && hs <= 0x3299) {
                    return true;
                } else if (hs == 0xa9 || hs == 0xae || hs == 0x303d
                        || hs == 0x3030 || hs == 0x2b55 || hs == 0x2b1c
                        || hs == 0x2b1b || hs == 0x2b50 || hs == 0x231a) {
                    return true;
                }
                if (!isEmoji && source.length() > 1 && i < source.length() - 1) {
                    char ls = source.charAt(i + 1);
                    if (ls == 0x20e3) {
                        return true;
                    }
                }
            }
        }
        return isEmoji;
    }
}
