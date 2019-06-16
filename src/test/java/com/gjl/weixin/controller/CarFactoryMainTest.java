package com.gjl.weixin.controller;

import com.gjl.weixin.factory.CarFactory;
import com.gjl.weixin.intefaceTest.Car;

public class CarFactoryMainTest {

    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Car bmv = carFactory.getNewInstance("Bmv");
        bmv.createCar();
        Car AudiCar = carFactory.getNewInstance("AudiCar");
        AudiCar.createCar();
    }
}
