package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description 后台线程，无论是否运行需要强制结束
 */
public class ThreadDemo6 {
    public static void main(String[] args){
        Thread rose = new Thread(){
            public void run(){
                for (int i = 0; i < 10; i++){
                    System.out.println("Let me go!");
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("oooooo");
            }
        };

        Thread jack = new Thread(){
            public void run(){
                while (true){
                    System.out.println("I jump");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        rose.start();
        //设置守护线程
        jack.setDaemon(true);
        jack.start();

        //进程不结束，后台进程一直执行
        //while (true);
        //gc后台线程
    }
}
