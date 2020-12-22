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
 * @date: 2020/12/16 20:35
 * @description: socket 连接服务端
 * @modified By:
 * @version: $ 1.0
 */
public class GreetServer {
    private ServerSocket  serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static final Logger log = LoggerFactory.getLogger(GreetServer.class);
    private static final String HELLO = "hello,Server";


    /**
     * 监听传入的服务端 端口，阻塞 accept
     * 双工通信，服务端也可以向客户端发送消息
     * @param port：端口
     */
    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            //outputStream
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            //inputStream
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

//            while (true){
                //读取客户端数据
            String greeting = in.readLine();
            if (greeting.equals(HELLO)) {
                //回复
                out.println("hello,Client");
            } else {
                //传入信息不对
                out.println("unrecognised greeting");
            }
//                System.out.println(greeting);
//                out.println(greeting+"-server");
//            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void stop(){
        //close stream and connection
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        GreetServer greetServer = new GreetServer();
        greetServer.start(6666);
        greetServer.stop();
    }
}
