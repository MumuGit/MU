package com.diabin.design_pattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.diabin.design_pattern.bridge.Abstraction;
import com.diabin.design_pattern.bridge.AbstractionImpl;
import com.diabin.design_pattern.bridge.Implementor;
import com.diabin.design_pattern.bridge.ImplementorImpl;
import com.diabin.design_pattern.proxy.DynamicProxy;
import com.diabin.design_pattern.proxy.IChannel;
import com.diabin.design_pattern.proxy.Proxy;
import com.diabin.design_pattern.proxy.Real;

public class ClientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bridge();
    }

    /**
     * 代理模式
     */
    private void proxy() {
        IChannel real = new Real();
        Proxy proxy = new Proxy(real);
        proxy.start();
    }

    private void dynamicProxy() {
        IChannel real = new Real();
        DynamicProxy dynamicProxy = new DynamicProxy(real);
        ClassLoader classLoader = real.getClass().getClassLoader();
        IChannel proxy = (IChannel) java.lang.reflect.Proxy.newProxyInstance(
                classLoader, new Class[]{IChannel.class}, dynamicProxy);
        proxy.start();
    }

    /**
     * 桥接模式
     */
    private void bridge() {
        Implementor implementor = new ImplementorImpl();
        Abstraction abstraction = new AbstractionImpl(implementor);
        abstraction.operationA();
    }

}