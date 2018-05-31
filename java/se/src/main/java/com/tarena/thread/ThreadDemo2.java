package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description Thread 第二种创建方法：实现Runnable接口，重写run方法。
 */
public class ThreadDemo2 {
    public static void main(String[] args){
        Runnable r1 = new MyRunable1();
        Runnable r2 = new MyRunable2();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }
}

/**
 * 1. 实现与线程无关的Runnable接口，接口可以多实现，不影响其他接口，也可以自行指定父类。
 * 2. 指定了线程要执行的任务，解决了线程和任务之间的耦合关系。
 */
class MyRunable1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++)
            System.out.println("Who are you?");
    }
}

class MyRunable2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++)
            System.out.println("I am you daddy!");
    }
}
