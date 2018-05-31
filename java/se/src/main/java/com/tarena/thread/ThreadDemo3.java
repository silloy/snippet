package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description 使用匿名内部类创建线程
 */
public class ThreadDemo3 {
    public static void main(String[] args){
        Thread t1 = new Thread(){
            public void  run(){

            }
        };

        Runnable r1 = new Runnable() {
            @Override
            public void run() {

            }
        };

        Thread t2 = new Thread(r1);
        t1.start();
        t2.start();
    }
}
