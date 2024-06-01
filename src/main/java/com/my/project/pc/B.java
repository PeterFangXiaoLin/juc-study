package com.my.project.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {
    // 生产者消费者问题
    public static void main(String[] args) {
        Data2 data2 = new Data2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "D").start();
    }
}

class Data2 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            // 业务代码
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "=> " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName() + "=> " + number);
            condition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
