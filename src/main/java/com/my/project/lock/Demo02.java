package com.my.project.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone2.sms();
        }, "A").start();

        new Thread(() -> {
            phone2.sms();
        }, "B").start();
    }
}

class Phone2 {
    Lock lock = new ReentrantLock(); // lock 锁必须配对否则就会死在里面

    public void sms() {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "sms");
            call();
        } finally {
            lock.unlock();
        }
    }

    public void call() {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "call");
        } finally {
            lock.unlock();
        }
    }
}
