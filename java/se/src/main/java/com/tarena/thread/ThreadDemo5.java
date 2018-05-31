package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description
 */
public class ThreadDemo5 {
    public static void main(String[] args){
        //priority : 0~10
        Thread t = Thread.currentThread();
        long id = t.getId();
        boolean isAlive = t.isAlive();
        boolean isDaemon = t.isDaemon();
        boolean isInterrupt = t.isInterrupted();
        System.out.println(id);

        Thread max = new Thread(){
            public void run(){
            }
        };
        max.setPriority(Thread.MAX_PRIORITY);
    }
}
