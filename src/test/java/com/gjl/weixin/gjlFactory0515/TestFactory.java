package com.gjl.weixin.gjlFactory0515;

/**
 * @Author: WilliamJL
 * @Date: 2019/5/15 16:32
 * @Version 1.0
 */
public class TestFactory {
    public static void main(String[] args) {
        String str = "student";
        String str2 = "teacher";
        Factory factory = new Factory();
        Student student =(Student) factory.getBean(str);
        Teacher teacher =(Teacher) factory.getBean(str2);
        student.create();
        teacher.create();

    }
}
