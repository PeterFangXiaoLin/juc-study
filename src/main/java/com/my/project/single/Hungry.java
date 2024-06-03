package com.my.project.single;

// 饿汉式
public class Hungry {

    // 调用之前就已经创建好了
    // 可能会浪费空间
    private Hungry() {

    }

    private static final Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
