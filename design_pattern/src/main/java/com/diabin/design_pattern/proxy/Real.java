package com.diabin.design_pattern.proxy;

public class Real implements IChannel {
    @Override
    public void start() {
        System.out.println("Real-start");
    }
}
