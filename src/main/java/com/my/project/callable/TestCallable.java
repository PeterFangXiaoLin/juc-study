package com.my.project.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread().start()只认识runnable,怎么启动callable
        // new Thread(new Runnable()).start();
        // new Thread(new FutureTask<V>()).start();
        // new Thread(new FutureTask<>(new Callable())).start();
        MyThread myThread = new MyThread();
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(myThread);
        new Thread(integerFutureTask, "A").start();
        new Thread(integerFutureTask, "B").start(); // 有缓存

        Integer i = integerFutureTask.get(); // 这个 get 方法可能会产生阻塞，把它放到最后
        System.out.println(i);
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()");
        return 1024;
    }
}
