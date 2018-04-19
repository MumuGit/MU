package com.diabin.design_pattern.proxy;

/**
 * 代理的目标之一是控制外部对于真实类的访问权限
 * 实现的关键是持有真实类的引用
 */
public class Proxy implements IChannel {
    private IChannel mReal;

    public Proxy(IChannel mReal) {
        this.mReal = mReal;
    }

    @Override
    public void start() {
        mReal.start();
    }
}
