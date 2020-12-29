package corejava.io.program.chatroom.v1.server;

import java.io.*;
import java.net.Socket;

/**
 * @author: ljf
 * @date: 2020/12/29 19:34
 * @description: 服务端socket线程，用来向客户端转发消息
 * @modified By：
 * @version: $ 1.0
 */
public class UserThread extends Thread {
    private final Socket clientSocket;
    private PrintWriter printWriter;
    private String userName; //客户端创建时输入用户名，网络传输获取
    private final ChatServer chatServer;

    public UserThread(Socket clientSocket, ChatServer chatServer) {
        this.clientSocket = clientSocket;
        this.chatServer = chatServer;
    }

    public String getUserName() {
        return this.userName;
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            printWriter = new PrintWriter(outputStream,true);

            //阻塞监听客户端发来的消息，然后转发给其他客户端
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            printUsers();
            //首条消息是 客户端姓名
            userName = bufferedReader.readLine();
            chatServer.addUserName(userName);
            System.out.println("connectedCount: " + chatServer.connectedCount.getAndIncrement()
                    + " new user connected: " + userName);

            String serverMessage = "new user " + userName + " connected";
            chatServer.broadMessage(serverMessage, this);

            //read 阻塞，直到客户端发来"bye"消息，断开连接
            String clientMessage;
            while (!(clientMessage = bufferedReader.readLine()).equals("bye")) {
                //拼接上当前socket的用户，转发给其他用户
                serverMessage = "[" + userName + "]: " + clientMessage;
                chatServer.broadMessage(serverMessage, this);
            }

            //与客户端socket断开连接
            chatServer.removeUser(userName, this);
            clientSocket.shutdownOutput();//立即关闭输出流
            clientSocket.close();

            //转发我离开的消息
            serverMessage = userName + "has quited";
            chatServer.broadMessage(serverMessage, this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向新链接的客户端发送当前服务器的用户列表
     */
    public void printUsers() {
        if (chatServer.hasUsers()) {
            printWriter.println("connected users: " + chatServer.getUserNames());
        } else {
            printWriter.println("no other users connected");
        }
    }

    /**
     * 向客户端发送消息
     *
     * @param message：消息内容
     */
    public void sendMessage(String message) {
        printWriter.println(message);
    }
}
