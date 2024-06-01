package com.my.project.util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Semap {
    public static void main(String[] args) {
        // 信号量的个数：限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                // acquire() 得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "拿到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // release() 释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
