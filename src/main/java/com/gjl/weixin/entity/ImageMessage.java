package com.gjl.weixin.entity;



/**
 *
 * @Description: 图片消息
 * @Parameters:
 * @Return:
 * @Create Date:
 * @Version: V1.00
 * @author: 来日可期
 */
public class ImageMessage extends BaseMessage{
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
