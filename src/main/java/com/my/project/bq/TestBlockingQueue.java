package com.my.project.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1() {
        ArrayBlockingQueue<String> blockingDeque = new ArrayBlockingQueue<>(3);

        System.out.println(blockingDeque.add("a"));
        System.out.println(blockingDeque.add("b"));
        System.out.println(blockingDeque.add("c"));

        // 取出队首元素
        System.out.println(blockingDeque.element());
        // 现在队列已满，我们尝试插入第4个元素
        //IllegalStateException: Queue full
        // System.out.println(blockingDeque.add("d"));

        System.out.println(blockingDeque.remove());
        System.out.println(blockingDeque.remove());
        System.out.println(blockingDeque.remove());

        // 队列已空，尝试移除
        // NoSuchElementException
        System.out.println(blockingDeque.remove());
    }

    /**
     * 有返回值，但不抛出异常
     */
    public static void test2() {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        // 队列满时返回false,但不抛出异常
        // System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        // 队列为空时，返回null
        System.out.println(arrayBlockingQueue.poll());
    }

    /**
     * 等待，阻塞（一直阻塞）
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        // 队列已满，没有位置，它会一直等
        arrayBlockingQueue.put("d");

        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        // 队列为空时，它会一直阻塞
        arrayBlockingQueue.take();
    }

    /**
     * 等待，阻塞（超时等待）
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(arrayBlockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        // 等待2s,时间到了就退出
        System.out.println(arrayBlockingQueue.offer("d", 2L, TimeUnit.SECONDS));

        System.out.println(arrayBlockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2L, TimeUnit.SECONDS));
        System.out.println(arrayBlockingQueue.poll(2L, TimeUnit.SECONDS));
        // 等待2s, 时间到了就放弃
        System.out.println(arrayBlockingQueue.poll(2L, TimeUnit.SECONDS));

    }
}
