package com.tarena.thread;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description
 */
public class ThreadDemo9 {
    private static Object obj = new Object();
    private static boolean isFinish = false; //成员属性

    public static void main(String[] args){
        //方法局部内部类引用内部变量需要使用final变量
        final Thread download = new Thread(){
            public void run(){
                System.out.println("Download pic start");
                for (int i = 1; i <= 100; i++){
                    System.out.println("download: %" + i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Completed pic.");
                isFinish = true;
                /*
                 * 图片下载完成，显示开始show线程
                 * wait 和 notify 需要加锁
                 */
                synchronized (obj){
                    obj.notify();
                }

                System.out.println("Download append start");
                for (int i = 1; i <= 100; i++){
                    System.out.println("download: %" + i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Completed append.");
                isFinish = true;
            }
        };


        Thread show =  new Thread(){
            public void run(){
                System.out.println("show picture");
                //等待下载完成
                try {
                    //download.join();
                    synchronized (obj){
                        obj.wait();
                    }
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
