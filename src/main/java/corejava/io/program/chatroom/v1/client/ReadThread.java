package corejava.io.program.chatroom.v1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author: ljf
 * @date: 2020/12/29 20:55
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class ReadThread extends Thread{
    private final ChatClient chatClient;
    private final Socket clientSocket;

    public ReadThread(Socket clientSocket,ChatClient chatClient){
        this.chatClient = chatClient;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (!chatClient.isClosed()) {
            try {
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String response = bufferedReader.readLine();
                System.out.println("\n" + response);

                // prints the username after displaying the server's message
                if (chatClient.getUserName() != null) {
                    System.out.print("[" + chatClient.getUserName() + "]: ");
                }
            } catch (SocketException se){ //TODO：正常退出替代这里
                System.out.println("quit");
                System.err.println(se.getMessage());
                break;
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}
