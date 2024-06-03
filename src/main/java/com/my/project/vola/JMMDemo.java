package com.my.project.vola;

import java.util.concurrent.TimeUnit;

public class JMMDemo {
    // 不加volatile 会死循环
    // 加volatile保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {  // 线程1 不知道主内存的变化不知道
            while (num == 0) {

            }
        }).start();

        TimeUnit.SECONDS.sleep(1L);

        num = 1;
        System.out.println(num);
    }
}
