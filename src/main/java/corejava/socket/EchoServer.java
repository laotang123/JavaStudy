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
 * @date: 2020/12/16 22:09
 * @description: 打印服务，服务端将客户端传入的数据直接打印，当遇到. 时终止连接
 * @modified By:
 * @version: $ 1.0
 */
public class EchoServer {
    private static final Logger log = LoggerFactory.getLogger(EchoServer.class);
    private PrintWriter out;
    private BufferedReader in;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static final String END = ".";

    public void start(int port) {
        //监听指定端口，accept阻塞直到有客户端进行连接
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if(inputLine.equals(END)){
                    out.println("good bye");
                    break;
                }else {
                    out.println(inputLine);
                    log.info(inputLine);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer();
        echoServer.start(4444);
    }
}
