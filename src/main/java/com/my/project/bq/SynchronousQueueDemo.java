package com.my.project.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "put a");
                TimeUnit.SECONDS.sleep(2L);
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "put b");

                TimeUnit.SECONDS.sleep(2L);
                synchronousQueue.put("c");
                System.out.println(Thread.currentThread().getName() + "put c");
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(Thread.currentThread().getName() + "take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2L);
                System.out.println(Thread.currentThread().getName() + "take " + synchronousQueue.take());
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "T2").start();
    }
}
