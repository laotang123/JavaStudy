package corejava.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: ljf
 * @date: 2020/12/16 22:27
 * @description: 创建 接收多个客户端进行echo服务的服务端
 * 服务端使用死循环创建accept 阻塞，每来一个客户端连接，就创建一个线程
 * @modified By:
 * @version: $ 1.0
 */
public class EchoMultiServer {
    private static final Logger log = LoggerFactory.getLogger(EchoMultiServer.class);
    private static final String END = ".";
    //single serverSocket multi clientSocket
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            //死循环创建监听clientSocket
            while (true) {
                Thread t = new Thread(new EchoClientSocket(serverSocket.accept()));
                log.info(String.format("create new thread %s for client socket",t.getName()));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private static class EchoClientSocket implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        public EchoClientSocket(Socket socket) {
            this.clientSocket = socket;
        }


        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String line;

                //除END特殊符号外，其他一律回传
                while ((line = in.readLine())!=null){
                    if(line.equals(END)){
                        out.println("good bye");
                    }else{
                        out.println(line);
                        log.info(line);
                    }
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer echoMultiServer = new EchoMultiServer();
        echoMultiServer.start(5555);
    }
}
