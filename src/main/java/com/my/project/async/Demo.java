package com.my.project.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调：CompletableFuture
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        // 没有返回值的异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(Thread.currentThread().getName() + "runAsync=>Void");
//        });
//
//        System.out.println("1111");
//        completableFuture.get(); // 获取阻塞执行结果

        // 有返回值的 suppleAsync 异步回调
        // ajax 成功和失败的回调
        // 返回的错误信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync=>Integer");
            int i = 10 / 0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((u, t) -> {
            System.out.println("u=>" + u); // 正常的返回结果
            System.out.println("t=>" + t); // 错误信息
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 222; // 失败的返回结果
        }).get());


    }
}
