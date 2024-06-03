package com.my.project.vola;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    // volatile 不保证原子性
    // private volatile static int num = 0;

    private static AtomicInteger num = new AtomicInteger(0);

    private static void add() {
//        num++; // 不是一个原子性操作
        num.getAndIncrement(); // Atomic + 1方法， CAS
    }

    public static void main(String[] args) {

        // 理论上执行完num = 20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
