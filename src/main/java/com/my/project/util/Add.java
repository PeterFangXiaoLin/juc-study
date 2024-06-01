package com.my.project.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 集齐7颗龙珠🐉召唤神龙
 * @author gaoshou
 */
public class Add {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("已集群7颗龙珠"));

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println("你已收集到第" + temp + "颗龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }
    }
}
