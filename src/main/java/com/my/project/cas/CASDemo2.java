package com.my.project.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo2 {
    // AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
    public static void main(String[] args) {
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp(); // 获取版本号
            System.out.println("A1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("A2=>" + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));

            System.out.println("A3=>" + atomicStampedReference.getStamp());

        }, "A").start();

        // 乐观锁
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println("B1=>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));

            System.out.println("B2=>" + atomicStampedReference.getStamp());
        }, "B").start();
    }
}
