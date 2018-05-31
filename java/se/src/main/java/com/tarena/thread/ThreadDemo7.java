package com.tarena.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description sleep使线程进入阻塞状态，指定ms
 */
public class ThreadDemo7 {
    public static void main(String[] args) {
        /*
         * 每过一秒输出一次当前系统时间 HH:MM:SS
         */
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        while (true){
            System.out.println(sdf.format(new Date()));
            try {
                Thread.sleep(1000);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
