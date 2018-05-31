package com.tarena.socket;

import jdk.nashorn.internal.runtime.ECMAException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author SuShaohua
 * @date 2016/7/27 13:49
 * @description
 */
public class Client {
    private Socket socket;

    //Client初始化：创建socket
    public Client() throws Exception{
        try {
            /*
             * 创建的过程就是连接的过程
             */
            socket = new Socket("localhost", 8088);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args){
        try{
            Client client = new Client();
            client.start();
        }catch (Exception e){
            System.out.println("Execute failure!");
        }
    }

    private void start() throws Exception{
        try {
            GetMessageHandler handler = new GetMessageHandler();
            Thread t = new Thread(handler);
            t.start();
            /**
             * 将数据发往server端
             */
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
            PrintWriter pw = new PrintWriter(osw, true);  //true自动行刷新
            Scanner input = new Scanner(System.in);
            while (true){
                pw.println(input.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private class GetMessageHandler implements Runnable {
        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String msg = null;
                while ((msg = br.readLine()) != null){
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
