package corejava.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author: ljf
 * @date: 2020/12/28 21:23
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class SocketClientV1 {
    public static String ip = "127.0.0.1";
    public static int port = 12345;
    //待连接的ip和port
    public static Socket clientSocket = null;


    public static void init() {
        try {
            clientSocket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Sender implements Runnable {


        @Override
        public void run() {
            OutputStream os = null;
            String message;
            try {
                os = clientSocket.getOutputStream();
                Scanner sc = new Scanner(System.in);
                System.out.println("sending...");
                while (!(message = sc.nextLine()).equals("exit")) {
                    os.write(message.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * read阻塞到当前线程
     */
    public static class Receiver implements Runnable {

        @Override
        public void run() {
            InputStream is;
            byte[] buffer = new byte[1024];
            int len = 0;
            StringBuilder sb = new StringBuilder();
            try {
                while (!clientSocket.isClosed()) {
                    is = clientSocket.getInputStream();
                    while ((len = is.read(buffer)) != -1) {
                        sb.append(new String(buffer, 0, len, StandardCharsets.UTF_8));
                    }
                    if (sb.length() > 0) {
                        System.out.println("get message from client: " + sb.toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stop() {
        if (clientSocket != null) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        init();
        Thread sender = new Thread(new Sender());
        Thread receiver = new Thread(new Receiver());

        sender.start();
        receiver.start();

        sender.join();
        receiver.join();
        stop();
    }


}
