package corejava.io.program.chatroom.v1.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: ljf
 * @date: 2020/12/29 20:46
 * @description: 创建阻塞的读线程和写线程
 * @modified By：
 * @version: $ 1.0
 */
public class ChatClient {
    private volatile String userName;
    private final String hostName;
    private final int port;
    private volatile boolean closed = false;

    public ChatClient(String hostName,int port){
        this.hostName = hostName;
        this.port = port;
    }

    public static void main(String[] args) {
        String hostName = "localhost";
        int port =  12345;

        ChatClient chatClient = new ChatClient(hostName, port);
        chatClient.execute();
    }
    /**
     * 与服务端建立连接
     */
    public void execute(){
        try {
            //必须输入用户名字后才能创建socket
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter your name: ");
            userName = scanner.nextLine();

            Socket clientSocket = new Socket(hostName, port);
            System.out.println("connected to chat server");
            new ReadThread(clientSocket,this).start();
            new WriteThread(clientSocket,this).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClosed(){
        closed = true;
    }
    public boolean isClosed(){
        return closed;
    }

    public String getUserName() {
        return userName;
    }
}
