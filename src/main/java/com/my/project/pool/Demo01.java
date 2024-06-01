package com.my.project.pool;

import java.util.concurrent.*;

// Executors 工具类、3大方法

/**
 * 四种拒绝策略
 * new ThreadPoolExecutor.AbortPolicy() // 银行人满了，还有人进来，不处理这个人，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy() // 从哪里来就回到哪里执行（打发）
 * new ThreadPoolExecutor.DiscardPolicy() // 队列满了，直接丢弃，不抛出异常
 * new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试去和最早执行的线程竞争，失败则丢弃，不抛出异常
 */
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);// 固定大小的线程池
//        ExecutorService threadPool = Executors.newCachedThreadPool();// 遇强则强，可伸缩
        // 自定义线程池！！！

        // 最大线程到底如何定义：
        // 1. CPU密集型，几核就是几，可以保持CPU的效率最高！
        // 2. IO 密集型  > 你程序中十分耗IO的线程数（一般是为该值的2倍）

        // 获取CPU的核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),
                3L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy() // 队列满了，尝试去和最早执行的线程竞争，失败则丢弃，不抛出异常
        );

        try {
            // 使用了线程池之后，使用线程池来创建线程
            // 最大承载 = 最大线程池数 + 阻塞队列的大小
            // 超过最大承载将触发拒绝策略
            for (int i = 1; i <= 9; i++) {
                final int temp = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " " + temp);
                });
            }
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
