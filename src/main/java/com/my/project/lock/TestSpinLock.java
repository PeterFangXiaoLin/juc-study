package com.my.project.lock;

import java.util.concurrent.TimeUnit;

public class TestSpinLock {
    public static void main(String[] args) throws InterruptedException {
//        Lock lock = new ReentrantLock();
//        lock.lock();
//        lock.unlock();

        MySpinlock mySpinlock = new MySpinlock();

        new Thread(() -> {
            mySpinlock.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                mySpinlock.myUnlock();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1L);

        new Thread(() -> {
            mySpinlock.myLock();

            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                mySpinlock.myUnlock();
            }
        }, "B").start();
    }
}
