package com.my.project.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition实现精准唤醒
 */

public class cd {

    public static void main(String[] args) {
        C c = new C();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.printC();
            }
        }, "C").start();
    }
}

class C {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> A");
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> B");
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + " => C");
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
