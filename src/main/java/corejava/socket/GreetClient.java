package corejava.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author: ljf
 * @date: 2020/12/16 21:53
 * @description: java socket服务端
 * @modified By:
 * @version: $ 1.0
 */
public class GreetClient {
    private static final Logger log = LoggerFactory.getLogger(GreetClient.class);
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * 创建客户端连接，
     *
     * @param ip：ip地址
     * @param port：端口
     */
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            //输入流
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 向服务端发送消息
     *
     * @param msg：消息体
     * @return 服务端回复的消息
     */
    public String sendMessage(String msg) {
        try {
            out.println(msg);
            return in.readLine();
        } catch (IOException e) {
            return null;

        }
    }

    public void stopConnection(){
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        GreetClient greetClient = new GreetClient();
//        greetClient.startConnection("localhost",6666);
        greetClient.startConnection("localhost",4444);
        System.out.println(greetClient.sendMessage("hello,Server1"));
        greetClient.stopConnection();
    }

}
