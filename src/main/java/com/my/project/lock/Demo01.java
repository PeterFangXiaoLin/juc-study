package com.my.project.lock;

public class Demo01 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(()-> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + "sms");
        call();
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + "call");
    }
}
