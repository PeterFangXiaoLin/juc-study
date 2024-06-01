package com.my.project.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁）一次只能被一个线程占有
 * 共享锁（读锁）多个线程可以同时占有
 * ReadWriteLock
 * 读-读 可以共存
 * 读-写 不能共存
 * 写-写 不能共存
 */
public class ReadWithWriteDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        // 5 个写入线程
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        // 5个读取线程
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }

    }
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();

    // 读写锁，更加细粒度的控制
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 存、写入的时候，只希望同时只有一个线程写
    public void put(String key, String value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // 读、取所有人都可以读
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // 存、写
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "写入完成");
    }

    // 取、读
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "读取" + key);
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "读取完成");
    }
}
