package com.diabin.design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    private Object mReal;
    public int a = 1;

    public DynamicProxy(Object mReal) {
        this.mReal = mReal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(mReal, args);
        /**
         * TODO 无法打印
         */
        System.out.println("测试此变量是否是此类实例：" + ((DynamicProxy) proxy).a);
        return result;
    }
}
