package jvm.modifycodebyte;

import corejava.callback.AuthenticationFilter;
import corejava.callback.Client;

import java.io.Serializable;

/**
 * @author: ljf
 * @date: 2021/1/17 23:39
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class ServerHandler implements Serializable {

    public void handle() {
        System.out.println("handle server service");
    }

    public void service() {
        int value = 1 + 4;
        System.out.println("service value: " + value);
    }

    public static void main(String[] args) {
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.handle();
        serverHandler.service();

        //当前只能实现跟踪当前类的System.out.println输出
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
//        Client client = new Client(authenticationFilter);
//        client.sendMessage("Downloading...");
    }
}
