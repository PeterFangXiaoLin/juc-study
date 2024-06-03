package com.my.project.single;

public class LazyMan {
    private LazyMan() {

    }

    private volatile static LazyMan lazyMan;

//    // 单线程ok, 多线程就有问题
//    public static LazyMan getINstance() {
//        if (lazyMan == null) {
//            lazyMan = new LazyMan();
//        }
//        return lazyMan;
//    }

    // 加锁
    // 双重检测锁模式的 饿汉式单例 DCL懒汉式
    public static LazyMan getLazyMan() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); // 不是一个原子性的操作
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化对象
                     * 3. 把这个对象指向这个空间
                     * 123
                     * 指令重排132
                     * 执行到3时B线程进来
                     *
                     */

                }
            }
        }
        return lazyMan;
    }
}
