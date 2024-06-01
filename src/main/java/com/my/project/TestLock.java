package com.my.project;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        // 并发：多个线程操作同一个资源

        Ticket2 ticket2 = new Ticket2();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket2.buy();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket2.buy();
            }
        }).start();
        new Thread(() -> {
            ticket2.buy();
        }).start();
    }
}

// lock 3 步曲：
// 1. new ReentrantLock
// 2. 加锁
// 3. 在 finally 释放锁
class Ticket2 {
    private int number = 50;

    Lock lock = new ReentrantLock();
    // synchronized 本质：排队
    public void buy() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "买了第" + (number--) + "张票，剩" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
