package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description  Thread join
 */
public class ThreadDemo8 {
    private static boolean isFinish = false; //成员属性
    public static void main(String[] args){
        //方法局部内部类引用内部变量需要使用final变量
         final Thread download = new Thread(){
            public void run(){
                System.out.println("Download start");
                for (int i = 1; i <= 100; i++){
                    System.out.println("download: %" + i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Completed.");
                isFinish = true;
            }
        };


        Thread show =  new Thread(){
            public void run(){
                System.out.println("show picture");
                //等待下载完成
                try {
                    download.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!isFinish){
                    throw new RuntimeException("Load failure");
                }
                System.out.println("picture loaded!");
            }
        };
        download.start();
        show.start();
    }
}
