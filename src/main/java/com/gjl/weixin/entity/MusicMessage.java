package com.gjl.weixin.entity;



/**
 *
 * @Description: 音乐消息
 * @Parameters:
 * @Return:
 * @Create Date:
 * @Version: V1.00
 * @author:来日可期
 */
public class MusicMessage extends BaseMessage{
    //音乐
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

}
