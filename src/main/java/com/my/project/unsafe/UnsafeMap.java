package com.my.project.unsafe;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UnsafeMap {
    public static void main(String[] args) {
        // 不安全
        // Map<String, String> map = new HashMap<>();
        // 解决：
        // 1. Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString());
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
