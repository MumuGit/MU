package com.diabin.design_pattern.bridge;

public class AbstractionImpl extends Abstraction {
    public AbstractionImpl(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operationA() {
        System.out.println("其他操作");
        super.operationA();

    }
}
