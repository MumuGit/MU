package com.diabin.design_pattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterViewAnimator;

import com.diabin.design_pattern.adapter.Volt220;
import com.diabin.design_pattern.adapter.VoltAdapter;
import com.diabin.design_pattern.bridge.Abstraction;
import com.diabin.design_pattern.bridge.AbstractionImpl;
import com.diabin.design_pattern.bridge.Implementor;
import com.diabin.design_pattern.bridge.ImplementorImpl;
import com.diabin.design_pattern.composite.Dir;
import com.diabin.design_pattern.composite.Folder;
import com.diabin.design_pattern.facade.Facade;
import com.diabin.design_pattern.flyweight.TrainTicket;
import com.diabin.design_pattern.flyweight.TicketFactory;
import com.diabin.design_pattern.proxy.DynamicProxy;
import com.diabin.design_pattern.proxy.IChannel;
import com.diabin.design_pattern.proxy.Proxy;
import com.diabin.design_pattern.proxy.Real;

public class ClientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter();
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

    /**
     * 外观模式
     */
    private void facade() {
        Facade facade = new Facade();
        facade.videoChat();
    }

    /**
     * 享元模式
     */
    private void flyweight() {
        TrainTicket ticket = TicketFactory.getTicket("北京", "青岛");
        ticket.printTicket("上铺");
    }

    private void adapter() {
        Volt220 volt220 = new Volt220();
        VoltAdapter adapter = new VoltAdapter(volt220);
        System.out.println(adapter.getVolt5());
    } 

    private void composite() {
        Dir disk = new Folder("C");
        
    }
}
