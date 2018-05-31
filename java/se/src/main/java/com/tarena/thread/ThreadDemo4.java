package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description static Thread currentThread() ： get current thread
 */
public class ThreadDemo4 {

    //jvm有一个前台线程来运行main方法
    public static void main(String[] args){
        Thread t = Thread.currentThread();
        System.out.println(t);
        dosome();

        Thread MyThread = new Thread(){
            public void run(){
                Thread t = Thread.currentThread();
                System.out.println(t);
                dosome();
            }
        };
        MyThread.start();
    }

    public static void dosome(){
        Thread t = Thread.currentThread();
        System.out.println(t);
    }
}
