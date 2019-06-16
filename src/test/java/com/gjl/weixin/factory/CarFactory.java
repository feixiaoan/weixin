package com.gjl.weixin.factory;

import com.gjl.weixin.intefaceTest.Car;
import com.gjl.weixin.intefaceTest.interfaceTestImpl.AudiCar;
import com.gjl.weixin.intefaceTest.interfaceTestImpl.BmvCar;

public class CarFactory {

    public  Car getNewInstance(String name){
        Car car = null;
        if("Bmv".equals(name)){
            car = new BmvCar();
        }else if("AudiCar".equals(name)){
            car = new AudiCar();
        }
        return car;
    }

}
