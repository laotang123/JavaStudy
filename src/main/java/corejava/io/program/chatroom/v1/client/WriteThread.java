package corejava.io.program.chatroom.v1.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: ljf
 * @date: 2020/12/29 20:59
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class WriteThread extends Thread {
    private final ChatClient chatClient;
    private final Socket clientSocket;
    private PrintWriter printWriter;

    public WriteThread(Socket clientSocket, ChatClient chatClient) {
        this.chatClient = chatClient;
        this.clientSocket = clientSocket;

        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            printWriter = new PrintWriter(outputStream,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        String userName = chatClient.getUserName();
        printWriter.println(userName);

        String text;
//        System.out.print("[" + userName + "]: ");
        while (!(text = scanner.nextLine()).equals("bye")) {
            printWriter.println(text);
            System.out.print("[" + userName + "]: ");
        }

        try {
            clientSocket.close();
        } catch (IOException ex) {

            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}
