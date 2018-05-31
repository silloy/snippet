package com.tarena.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/24
 * @description
 * TODO Socket framework: MINA
 */
public class UDPServer {
    public static void main(String[] args){
        UDPServer server = new UDPServer();
        /*
         * 1. 创建socket
         * 2. 准备接收数据用的包
         * 3. 将包交给socket用于接收数据
         * 4. 取数据
         */
        try{
            DatagramSocket socket= new DatagramSocket(8088);

            byte[] data = new byte[100];
            DatagramPacket packet = new DatagramPacket(data, data.length);

            socket.receive(packet);

            String messsage = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
            System.out.println(messsage);

            //回复客户端
            String str = "hello, I' m server";
            data = str.getBytes("UTF-8");
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
