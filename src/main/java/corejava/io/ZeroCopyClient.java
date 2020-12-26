package corejava.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author: ljf
 * @date: 2020/12/24 17:15
 * @description:
 * @modified By：
 * @version: $ 1.0
 */

class ZerocopyClient {
    public static void main(String[] args) throws IOException {
        ZerocopyClient sfc = new ZerocopyClient();
        sfc.testSendfile();
    }

    public void testSendfile() throws IOException {
//        String host = "192.168.56.129";
        String host = "localhost";
        int port = 9026;
        SocketAddress sad = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        String fileName = "data/a.txt";
        FileChannel fc = new FileInputStream(fileName).getChannel();
        long start = System.nanoTime();
        long sendSize;
        sendSize = fc.transferTo(0, fc.size(), sc);
        System.out.println("发送的总字节数:" + sendSize + " 耗时(ns):" + (System.nanoTime() - start));
        try {
            sc.close();
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
