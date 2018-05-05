package com.diabin.design_pattern.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 说白了就是用map使实例复用，并共享内部性质如出发地、外部性质上铺
 */
public class TicketFactory {
    private static Map<String, TrainTicket> sPool = new ConcurrentHashMap<>();

    public static TrainTicket getTicket(String from, String to) {
        String key = from + "-" + to;
        if (sPool.containsKey(key)) {
            System.out.println("使用缓存" + key);
            return sPool.get(key);
        } else {
            System.out.println("创建对象" + key);
            TrainTicket ticket = new TrainTicketImpl(from, to);
            sPool.put(key, ticket);
            return ticket;
        }

    }

}
