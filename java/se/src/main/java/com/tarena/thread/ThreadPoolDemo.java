package com.tarena.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/24
 * @description pool : 重用，控制数量
 * ExecutorService
 */
public class ThreadPoolDemo {
    public static void main(String[] args){
        ExecutorService threadPool;
        threadPool = Executors.newCachedThreadPool();
        /*
         * 创建线程数量为2的线程池
         */
        threadPool = Executors.newFixedThreadPool(2);
        Executors.newScheduledThreadPool(1000);
        Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread t = Thread.currentThread();
                        System.out.println(t + "tasking");
                        Thread.sleep(5000);
                        System.out.println(t + "task complete");
                    }catch (Exception e){
                    }
                }
            };
            threadPool.execute(runnable);
        }
        System.out.println("main end thread");
        threadPool.shutdown();
    }
}
