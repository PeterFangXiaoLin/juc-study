package com.my.project.util;

import java.util.concurrent.CountDownLatch;

public class Down {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " GO OUT");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println("所有人都走了");
    }
}
