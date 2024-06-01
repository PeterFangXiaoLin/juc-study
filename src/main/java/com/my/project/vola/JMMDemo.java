package com.my.project.vola;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    private static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        TimeUnit.SECONDS.sleep(1L);

        num = 1;
        System.out.println(num);
    }
}
