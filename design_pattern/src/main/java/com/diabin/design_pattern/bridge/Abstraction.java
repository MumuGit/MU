package com.diabin.design_pattern.bridge;

/**
 * 两个抽象类之间具有联系，抽象Abstraction和实现都是单独的抽象类，所以实现了解耦
 */
public abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }


    public void operationA() {
        implementor.operationB();
        implementor.operationC();
    }
}
