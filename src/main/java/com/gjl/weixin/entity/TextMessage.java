package com.gjl.weixin.entity;

/**
 *
 * @Description: 文本消息
 * @Parameters:
 * @Return:
 * @Create Date:
 * @Version: V1.00

 */
public class TextMessage extends BaseMessage{
    //回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

}
