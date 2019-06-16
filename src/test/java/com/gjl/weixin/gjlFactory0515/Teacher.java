package com.gjl.weixin.gjlFactory0515;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/15 16:27
 * @Version 1.0
 */
public class Teacher implements Person {
    @Override
    public void create() {
        System.out.println("生产出一个教师");
    }
}
