package com.hua.server;

import com.hua.MyObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Elon
 * @title: Server
 * @projectName: Progressive-RPC-freamework
 * @description: TODO
 * @date: 2025/2/23 18:08
 */
public class Server {

    // 服务端监听端口
    static int port = 12345;


    public static void main(String[] args) throws IOException {
        runWithObject();
    }

    public static void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        try{
            Socket clientSocket = serverSocket.accept();
            System.out.println("Acceptd connection from " +  clientSocket.getInetAddress().getHostAddress());

            InputStream in = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // 使用循环来读取数据、模拟粘包和半包问题
            while(true){
                String message = reader.readLine();
                // 如果读取到null,说明客户端关闭了连接
                if(message == null) {
                    break;
                }
                System.out.println("Received message from client: " + message);

                clientSocket.close();
            }
        }finally {
            serverSocket.close();
        }
    }

    // 解决粘包
    public static void resolveRunWithStickyBag() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

            InputStream in = clientSocket.getInputStream();
            DataInputStream dis = new DataInputStream(in);

            while (true) {
                // 读取消息长度
                int length = dis.readInt();
                byte[] buffer = new byte[length];
                // 根据长度读取消息体
                dis.readFully(buffer);
                String message = new String(buffer);
                System.out.println("Received message from client: " + message);
            }
        } catch (EOFException e){
            // 客户端关闭了连接，退出循环
            System.out.println("End of stream reached. Client closed the connection.");
        }finally {
            serverSocket.close();
        }
    }

    // 半包问题
    public static void runWithHalfPackage() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

            InputStream in = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // 模拟半包问题：只读取消息的一部分
            char[] buffer = new char[20];
            // 定义一个小的缓冲区来模拟半包
            int charsRead = reader.read(buffer, 0, buffer.length);
            while (charsRead != -1) {
                // 将读取的部分打印出来
                String partialMessage = new String(buffer, 0, charsRead);
                System.out.println("Received partial message: " + partialMessage);

                // 再次读取剩余的消息部分
                charsRead = reader.read(buffer, 0, buffer.length);
            }

            clientSocket.close();
        } finally {
            serverSocket.close();
        }
    }

    public static void runWithObject() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + clientSocket.getInetAddress().getHostAddress());

            // 使用对象输入流接收对象
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            // 接收客户端发送的对象
            MyObject myObject = (MyObject) in.readObject();
            System.out.println("Received object from client: " + myObject);

            in.close();
            clientSocket.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            serverSocket.close();
        }
    }
}
