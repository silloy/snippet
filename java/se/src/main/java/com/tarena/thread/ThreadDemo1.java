package com.tarena.thread;

/**
 * @author SuShaohua
 * @date 2016/7/27 17:57
 * @description
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread t1 = new MyThread1();
        Thread t2 = new MyThread2();
        t1.start();
        t2.start();
    }
}

class MyThread1 extends Thread{
    public void run(){
        for (int i = 0; i < 1000; i++)
            System.out.println("Who are you?");
    }
}

class MyThread2 extends Thread{
    public void run(){
        for (int i = 0; i < 1000; i++)
            System.out.println("I am you daddy!");
    }
}
