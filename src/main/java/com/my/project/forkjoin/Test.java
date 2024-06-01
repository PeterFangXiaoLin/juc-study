package com.my.project.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1();// 384
        //test2(); // 231
        test3();//181
    }

    public static void test1() {
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 1; i <= 1_000_000_000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "time=" + (end - start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinTask<Long> forkJoinTask = new ForkJoinDemo(1L, 1_000_000_000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTask);
        long sum = submit.get();
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "time=" + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 1_000_000_000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + "time=" + (end - start));
    }
}
