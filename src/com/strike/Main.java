package com.strike;


public class Main {

    public static void main(String[] args) {
        Item item = new Item();
        Thread tA = new AThread(item);
        Thread tB = new BThread(item);
        tA.start();
        tB.start();

    }

}
