package com.my.project.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算的任务
 * 3000（暴力） 6000（ForkJoin） 9000（Stream并行流）
 * 如何使用 ForkJoin
 * 1. 通过 ForkJoinPool 来执行
 * 2. 计算任务 forkJoinPool.execute(ForkJoinTask task)
 * 3. 计算类要继承 ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;

    // 临界值
    private long temp = 10000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long mid = start + end >> 1;
            ForkJoinDemo fork1 = new ForkJoinDemo(start, mid);
            fork1.fork(); // 把任务压入线程队列
            ForkJoinDemo fork2 = new ForkJoinDemo(mid + 1, end);
            fork2.fork();
            return fork1.join() + fork2.join();
        }
    }
}
