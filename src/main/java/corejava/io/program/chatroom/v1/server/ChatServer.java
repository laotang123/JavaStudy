package corejava.io.program.chatroom.v1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: ljf
 * @date: 2020/12/29 19:25
 * @description: 聊天室服务端
 * 功能：
 * 1.数据转发broadcast
 * 功能推导类属性：
 * 1.userNames
 * 2.userThreads
 * 3.port 监听端口
 * <p>
 * accept阻塞监听客户端的链接，将链接添加到userNames，userThreads并启动用户线程接收数据
 * 服务端的userThread只用来转发数据，read and write
 * TODO：多个客户端同时退出的并发问题
 * socket全双工通信
 * @modified By：
 * @version: $ 1.0
 */
public class ChatServer {
    private final int port;
    private final HashSet<String> userNames = new HashSet<>();
    private final HashSet<UserThread> userThreads = new HashSet<>();
    private int connectedCount = 0;

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        //监听端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("chat server listening on port:" + port);
            while (true){
                //创建服务端进程，accept阻塞监听
                Socket clientSocket = serverSocket.accept();

                UserThread newUser = new UserThread(clientSocket, this);
                System.out.println("connectedCount: " + connectedCount + "new user connected: " + newUser.getUserName());
                connectedCount++;
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 12345;
        ChatServer chatServer = new ChatServer(port);
        chatServer.execute();
    }

    public void broadMessage(String serverMessage, UserThread userThread) {
        for (UserThread user : userThreads) {
            if (user != userThread) { //除当前用户
                user.sendMessage(serverMessage);
            }
        }
    }

    public Set<String> getUserNames(){
        return this.userNames;
    }

    /**
     * 删除用户，删除userName和userThread
     */
    public void removeUser(String user, UserThread userThread) {
        boolean removed = userNames.remove(user);
        if (removed) {
            userThreads.remove(userThread);
            System.out.println(user + "quit group chat");
        }
    }

    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }

    public void addUserName(String userName) {
        this.userNames.add(userName);
    }
}
