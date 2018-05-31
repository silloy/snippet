package com.tarena.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SuShaohua
 * @date 2016/7/27 13:16
 * @description
 */
public class Server {
    /*
     * A.负责申请端口，以便客户端连接;
     * B.接受客户端连接，并生成与客户端通讯的Socket。
     */
    private ServerSocket server;
    private List<PrintWriter> allout;
    public Server() throws IOException{
        try {
            allout = new ArrayList<>();
            /**
             * 创建server， 申请端口
             * socket accept() 一直监听端口，一旦客户端与之连接，即返回与该客户端连接的socket
             */
            server = new ServerSocket(8088);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private synchronized void addOut(PrintWriter out) {
        allout.add(out);
    }
    private synchronized void removeOut (PrintWriter out) {
        allout.remove(out);
    }
    private synchronized void sendMessageToAllClient(String msg){
        for (PrintWriter out : allout){
            out.print(msg);
        }
    }

    public void start() throws Exception{
        while (true){
            try {
                System.out.println("Waiting......");
                Socket socket = server.accept();
                System.out.println("One client connecting......");

                /**
                 * 启动线程负责交互
                 */
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * 服务端接受多客户端信息
     */
    private class ClientHandler implements Runnable {
        //与客户端交互的客户端
        private Socket socket;
        private String host;
        /*
         * 创建任务同时将socket一同传入
         * @param socket
         */
        public ClientHandler(Socket socket){
            this.socket = socket;
            InetAddress address = socket.getInetAddress();
            host = address.getHostAddress();
        }
        @Override
        public void run() {
            PrintWriter pw = null;
            /*
             * 通过socket 获取输入流
             * 通过socket 获取输出流，发送给客户端
             */
            try {
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter ose = new OutputStreamWriter(out, "UTF-8");
                pw = new PrintWriter(ose,  true);
                //将该客户端输出流存入共享
                addOut(pw);

                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String message = null;
                while (true){
                    // TODO: 2016/7/27
                    message = br.readLine();
                    /*
                     * operation system disconnected, server's reaction is different.
                     */
                    System.out.println(host + " : " + message);
                    sendMessageToAllClient(host + " : " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    System.out.println("client discconnected");
                    //将客户端从共享集合里删除
                    removeOut(pw);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static void main(String[] args){
        try{
            Server server = new Server();
            server.start();
        } catch (Exception e) {
            System.out.println("Server started failure");
            e.printStackTrace();
        }
    }
}
