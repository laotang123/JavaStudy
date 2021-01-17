package jvm.modifycodebyte;

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

    public static void main(String[] args) {
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.handle();
    }
}
