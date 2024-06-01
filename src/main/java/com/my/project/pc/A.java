package com.my.project.pc;

/**
 * 线程之间的通信问题：生产者和消费者问题 等待唤醒，通知唤醒
 * 线程交替执行 A B 操作同一个变量  num = 0
 * A num+1
 * B num-1
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "B").start();

        new Thread(() -> {
            data.increment();
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        }, "D").start();
    }
}

// 判断等待 业务 通知唤醒
class Data {
    private int number = 0;

    public synchronized void increment() {
        while (number != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }

    public synchronized void decrement() {
        while (number == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "=>" + number);
        this.notifyAll();
    }
}
