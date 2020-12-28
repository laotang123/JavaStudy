package corejava.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author: ljf
 * @date: 2020/12/28 21:06
 * @description: 阻塞io socket
 * 聊天室实现：
 * 功能1：主线程用于创建连接客户端的子线程
 * 功能2：每个客户端向服务端发送消息后，要向其他已经建立的连接发送消息
 * @modified By:
 * @version: $ 1.0
 */
public class SocketServerV1 {
    private static final int USER_LEN = 10;
    private static int current = 0;
    private static final NamedSocket[] GROUP_USERS = new NamedSocket[USER_LEN];
    private static final Logger log = LoggerFactory.getLogger(SocketServerV1.class);

    public static void addUser(String name, Socket clientSocket) throws IOException {
        if (current < USER_LEN) {
            GROUP_USERS[current] = new NamedSocket(name, clientSocket);
            log.info("name: " + name + " socket add successful");
            return;
        }
        log.info("name socket create error! " + name);
    }

    /**
     * 设置 定时时间，返回处于就绪态可读的socket
     *
     * @return
     */
    public static HashSet<NamedSocket> listen(int timeout) {
        HashSet<NamedSocket> readySet = new HashSet<>();
        InputStream is = null;
        try {
            Thread.sleep(timeout);
            for (NamedSocket user : GROUP_USERS) {
                if (user != null && !user.getSocket().isClosed()) {
                    is = user.getSocket().getInputStream();
                    if (is.available() != 0) {
                        readySet.add(user);
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return readySet;
    }

    public static String readData(Socket socket) {
        InputStream is;
        byte[] buffer = new byte[1024];
        int len = 0;
        StringBuilder sb = new StringBuilder();
        try {
            is = socket.getInputStream();
            while ((len = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 转发数据，读取readySet中inputStream的数据。转发到其他outputStream
     *
     * @param readySet：待读取数据集
     * @return: 发送数据的用户数量
     */
    public static int transferMessage(HashSet<NamedSocket> readySet) throws IOException {
        if (readySet.isEmpty()) {
            return 0;
        }

        for (NamedSocket namedSocket : readySet) {
            String data = readData(namedSocket.getSocket());
            for (NamedSocket user : GROUP_USERS) {
                OutputStream os = user.getSocket().getOutputStream();
                os.write(data.getBytes());
            }
        }

        return readySet.size();
    }

    public static class Processor implements Runnable {

        @Override
        public void run() {
            while (true) {
                HashSet<NamedSocket> sockets = listen(10);
                try {
                    int readyCount = transferMessage(sockets);
                    if (readyCount != 0) {
                        log.info("ready set size: " + readyCount);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //监听指定的端口
        int port = 12345;
        ServerSocket serverSocket = new ServerSocket(port);
        Thread processor = new Thread(new Processor());
        processor.start();
//        processor.join();

        //accept阻塞运行
        System.out.println("accepting...");
        String name = "duo";
        int id = 0;
        while (true) {
            Socket clientSocket = serverSocket.accept();
            addUser(name + id, clientSocket);
            id++;
        }
//        InputStream is = clientSocket.getInputStream();
//        byte[] buffer = new byte[1024];
//
//        //使用接收缓存接收数据
//        int bufferLen;
//        StringBuilder sb = new StringBuilder();
//
//        while ((bufferLen = is.read(buffer)) != -1) {
//            sb.append(new String(buffer, 0, bufferLen, StandardCharsets.UTF_8));
//        }
//
//        System.out.println("get message from client: " + sb.toString());
//        is.close();
//        clientSocket.close();
//        serverSocket.close();
    }
}
