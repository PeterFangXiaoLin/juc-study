package com.my.project.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// ConcurrentModificationException
public class UnsafeList {
    public static void main(String[] args) {
        // 并发下 ArrayList 不安全
        /**
         * 解决方案：
         * 1. List<String> list = new Vector<>();
         * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3. List<String> list = new CopyOnWriteArrayList<>();
         */
        // CopyOnWrite 写入时复制 COW 是计算机的一种优化策略
        // 多个线程调用的时候，list 读取的时候，没问题，写入时可能出现覆盖
        // 读写分离
        // CopyOnWrite 比 Vector NB 在哪？
        // 使用lock 代替 synchronized 提高效率

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
