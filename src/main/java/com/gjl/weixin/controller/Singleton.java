package com.gjl.weixin.controller;

import java.util.ArrayList;

public class Singleton {

    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        //多线程线程安全，w,r,r,r,r,r,
        if (instance == null) {
            synchronized(Singleton.class){//缓解多次读的时间问题
                if(instance==null){//多线程线程安全问题
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    //hashMap和双向链表 可以解决  时间复杂度由o(n)变成o(1);


}
