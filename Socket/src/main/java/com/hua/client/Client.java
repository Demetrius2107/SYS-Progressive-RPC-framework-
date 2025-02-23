package com.hua.client;

import com.hua.MyObject;

import java.io.*;
import java.net.Socket;

/**
 * @author: Elon
 * @title: Client
 * @projectName: Progressive-RPC-freamework
 * @description: TODO
 * @date: 2025/2/23 18:08
 */
public class Client {

    // 服务端的主机名或者ip地址
    static String hostName = "localhost";

    // 服务端监听的端口号
    static int port = 12345;


    public static void main(String[] args) throws IOException {
        runWithObject();
    }

    public static void run() throws IOException {
        Socket socket = new Socket(hostName, port);
        System.out.println("Connected to server at " + hostName + ":" + port);

        OutputStream outToServer = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outToServer, true);
        String messageToSend = "Hello,Server";
        out.println(messageToSend);

        socket.close();
        System.out.println("发送完毕");
    }

    // 模拟粘包问题
    public static void runWithStickyBag() throws IOException {
        Socket socket = new Socket(hostName, port);
        System.out.println("Connected to server at " + hostName);
        OutputStream outputStream = socket.getOutputStream();

        // 发送多个小消息，模拟粘包和半包问题
        String[] messages = {"Hello,", "Server!", "How", "are", "you?"};

        for (String message : messages) {
            outputStream.write(message.getBytes());
            // 确保立即发送
            outputStream.flush();
        }

        socket.close();
    }

    // 解决粘包问题
    public static void resolveRunWithStickyBag() throws IOException {

        Socket socket = new Socket(hostName, port);
        System.out.println("Connected to Server at " + hostName);
        DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());

        // 发送多个小消息，模拟粘包和半包问题
        String[] messages = {"Hello,", "Server!", "How", "are", "you?"};
        for (String message : messages) {
            // 发送消息长度
            outToServer.writeInt(message.length());
            // 发送消息
            outToServer.writeBytes(message);
            outToServer.flush();
        }

        socket.close();
    }

    // 模拟半包问题
    public static void runWithHalfPackage() throws IOException {
        Socket socket = new Socket(hostName, port);

        OutputStream outToServer = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outToServer, true);
        // 发送一个长消息，模拟半包问题
        String longMessage = "This is a very long message that will be split into two parts to simulate a half package issue.";
        out.println(longMessage);
        socket.close();
    }

    public static void runWithObject() throws IOException {
        Socket socket = new Socket(hostName,port);
        System.out.println("Connected to server at " + hostName);

        // 创建要发送的对象
        MyObject myObject = new MyObject("Hello Object","Test Message");

        // 使用对象输出流发送对象
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(myObject);

        out.close();
        socket.close();
    }


}
