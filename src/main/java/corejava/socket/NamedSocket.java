package corejava.socket;

import java.net.Socket;

/**
 * @author: ljf
 * @date: 2020/12/28 21:58
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class NamedSocket {
    private final String name;
    private final Socket socket;

    public NamedSocket(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }
}
