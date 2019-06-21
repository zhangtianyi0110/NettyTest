package com.zhangtianyi.nio.Decorator;

/**
 * 装饰角色（Decorator）
 * 比如FilterInputStream
 */
public class Decorator implements Componnet{

    Componnet componnet;

    public Decorator(Componnet componnet) {
        this.componnet = componnet;
    }
    @Override
    public void doSomething() {
        componnet.doSomething();
    }
}
