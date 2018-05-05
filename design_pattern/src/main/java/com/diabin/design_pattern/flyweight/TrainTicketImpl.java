package com.diabin.design_pattern.flyweight;

public class TrainTicketImpl extends TrainTicket {


    public TrainTicketImpl(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public void printTicket(String bunk) {
        System.out.println("从" + from + "到" + to + ":" + bunk);
    }
}
