package com.zhangtianyi.nio.Decorator;

/**
 * 测试类
 */
public class Client {


    public static void main(String[] args) {
        //未装饰
        new ConcreteComponnet().doSomething();
        System.out.println("----------------------------");
        //装饰一次
        Componnet componnet = new ConcreteDecorator_1(new ConcreteComponnet());
        componnet.doSomething();
        System.out.println("----------------------------");
        //装饰2次
        Componnet componnet2 = new ConcreteDecorator_2(new ConcreteDecorator_1(new ConcreteComponnet()));
        componnet2.doSomething();
        System.out.println("----------------------------");
    }

}
