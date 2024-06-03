package com.my.project.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {

    // CAS compareAndSet 比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);
        // 期望 更新
        // 如果当前值和我期望的值一样，则更新，否则不更新，CAS 是CPU的并发原语
        // ====== 捣乱的线程 =======
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2021, 2020));
        System.out.println(atomicInteger.get());

        // ====== 期望的线程 =======
        System.out.println(atomicInteger.compareAndSet(2020, 2021));
        System.out.println(atomicInteger.get());
    }
}
