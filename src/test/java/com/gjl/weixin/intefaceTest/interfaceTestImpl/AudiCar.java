package com.gjl.weixin.intefaceTest.interfaceTestImpl;

import com.gjl.weixin.intefaceTest.Car;

public class AudiCar implements Car {
    @Override
    public void createCar() {
        System.out.println("生产出AudiCar的车");
    }
}
