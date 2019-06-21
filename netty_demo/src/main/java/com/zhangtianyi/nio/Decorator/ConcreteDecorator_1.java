package com.zhangtianyi.nio.Decorator;

/**
 * 具体装饰角色_1（Concrete Decorator）
 * 比如 BufferedInputStream
 */
public class ConcreteDecorator_1 extends Decorator {

    public ConcreteDecorator_1(Componnet componnet) {
        super(componnet);
    }

    @Override
    public void doSomething() {

        super.doSomething();
        this.doOtherThing();
    }

    private void doOtherThing(){
        System.out.println("功能B");
    }
}
