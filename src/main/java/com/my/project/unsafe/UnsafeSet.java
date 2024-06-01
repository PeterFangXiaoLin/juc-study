package com.my.project.unsafe;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class UnsafeSet {
    public static void main(String[] args) {
        // 不安全 java.util.ConcurrentModificationException
        // Set<String> set = new HashSet<>();
        // 解决方法：
        // 1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
