package com.diabin.design_pattern.bridge;

public class ImplementorImpl extends Implementor {

    @Override
    public void operationB() {
        //windows平台代码
        System.out.println("windows-operationB");
    }

    @Override
    public void operationC() {
        //windows平台代码
        System.out.println("windows-operationC");
    }
}
