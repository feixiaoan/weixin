package com.gjl.weixin.intefaceTest.interfaceTestImpl;

import com.gjl.weixin.intefaceTest.Car;

public class BmvCar implements Car {
    @Override
    public void createCar() {
        System.out.println("生产出Bmv类型的车");
    }
}
