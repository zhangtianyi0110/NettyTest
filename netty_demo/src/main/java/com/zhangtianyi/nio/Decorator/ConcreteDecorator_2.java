package com.zhangtianyi.nio.Decorator;

/**
 * 具体装饰角色_2（Concrete Decorator）
 * 比如 DataInputStream
 */
public class ConcreteDecorator_2 extends Decorator {

    public ConcreteDecorator_2(Componnet componnet) {
        super(componnet);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doOtherThing();
    }

    private void doOtherThing() {
        System.out.println("功能C");
    }
}
