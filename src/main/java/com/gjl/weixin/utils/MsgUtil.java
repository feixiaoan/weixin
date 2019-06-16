package com.gjl.weixin.utils;

import com.gjl.weixin.entity.*;

public class MsgUtil {

    /**
     * 组装菜单
     * @return
     */
    public static Menu initMenu(){
        Menu menu = new Menu();
        ViewButton button11 = new ViewButton();
        //注意按钮名字不要太长，不然会报40018错误
        button11.setName("齐市电网");
        button11.setType("view");
        button11.setUrl("http://www.hl.sgcc.com.cn/html/qqhr/col901/column_901_1.html");
        //button11.setUrl("http://hw8tp4.natappfree.cc/index.html");
        //注意链接不要少了https://  否则会报错40055

        SendPicButton button21 = new SendPicButton();
        button21.setName("发图");
        button21.setType("pic_photo_or_album");
        button21.setKey("pic");

        SendLocalButton button32 = new SendLocalButton();
        button32.setName("发位置");
        button32.setType("location_select");
        button32.setKey("local");

       /* ClickButton button31 = new ClickButton();
        button31.setName("点赞");
        button31.setType("click");
        button31.setKey("strtest");//事件key*/
        ViewButton button31 = new ViewButton();
        button31.setName("调查问卷");
        button31.setType("view");
        button31.setUrl("http://sbxwun.natappfree.cc/success.html");
        ViewButton button33 = new ViewButton();
        //注意按钮名字不要太长，不然会报40018错误
        button33.setName("投诉");
        button33.setType("view");
        button33.setUrl("http://sbxwun.natappfree.cc/complain.html");
        ViewButton button34 = new ViewButton();
        //注意按钮名字不要太长，不然会报40018错误
        button34.setName("登陆");
        button34.setType("view");
        button34.setUrl("http://sbxwun.natappfree.cc/indexlogin.html");


        Button button = new Button();
        button.setName("功能列表");
        button.setSub_button(new Button[]{button34,button31,button32,button33});
        //button.setSub_button(new Button[]{button31,button32});


        menu.setButton(new Button[]{button11,button21,button});
        return menu;
    }
}
