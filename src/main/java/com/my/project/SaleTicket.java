package com.my.project;


/**
 * 真正的多线程开发，降低耦合性
 * 线程就是一个单独的资源类，没有任何附属的操作
 * 属性、方法
 */
public class SaleTicket {
    public static void main(String[] args) {
        // 并发：多个线程操作同一个资源

        Ticket2 ticket = new Ticket2();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.buy();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.buy();
            }
        }).start();
        new Thread(() -> {
            ticket.buy();
        }).start();
    }
}

// OOP
class Ticket {
    private int number = 50;

    // synchronized 本质：排队
    public synchronized void buy() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "买了第" + (number--) + "张票，剩" + number);
        }
    }
}