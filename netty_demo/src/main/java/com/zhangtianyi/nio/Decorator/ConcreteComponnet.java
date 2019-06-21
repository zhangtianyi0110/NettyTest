package com.zhangtianyi.nio.Decorator;

/**
 * 具体构件角色（Concrete Component）
 * 例如FileInputStream
 */
public class ConcreteComponnet implements Componnet {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
