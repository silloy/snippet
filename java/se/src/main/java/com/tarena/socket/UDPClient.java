package com.tarena.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/24
 * @description
 */
public class UDPClient {
    public static void main(String[] args){

        UDPClient client = new UDPClient();
        /*
         * 1.创建UDP通讯的socket
         * 2.准备数据
         * 3.打包：填地址，放数据
         * 4.通过socket发送
         */
        try{
            DatagramSocket socket = new DatagramSocket();

            String str = "Hello, I' m client!";
            byte[] data = str.getBytes("UTF-8");

            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port =  8088;

            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

            socket.send(packet);

            //等待服务端回复
            data = new byte[100];
            packet = new DatagramPacket(data, data.length);

            socket.receive(packet);

            String messsage = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            System.out.println(messsage);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
