package com.tarena.thread;

import javax.swing.*;

/**
 * @author SuShaohua
 * @date 2016/8/20 10:54
 * @description
 */
public class TestRunnable extends JFrame implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (i<10){
            i++;
        }
        System.out.println("i :" + i);
    }
    public static void main(String[] args) {
        Runnable r = new TestRunnable();

        r.run();
    }
}



