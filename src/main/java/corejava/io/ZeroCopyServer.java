package corejava.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2020/12/24 17:10
 * @description: 将本地的文件通过nio 零拷贝到网卡中传输到指定socket
 * @modified By：
 * @version: $ 1.0
 */
public class ZeroCopyServer {
    ServerSocketChannel listener = null;

    protected void mySetup() {
        InetSocketAddress listenAddr = new InetSocketAddress(9026);

        try {
            listener = ServerSocketChannel.open();
            ServerSocket ss = listener.socket();
            ss.setReuseAddress(true);
            ss.bind(listenAddr);
            System.out.println("监听的端口:" + listenAddr.toString());
        } catch (IOException e) {
            System.out.println("端口绑定失败 : " + listenAddr.toString() + " 端口可能已经被使用,出错原因: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ZeroCopyServer dns = new ZeroCopyServer();
        dns.mySetup();
        dns.readData();
    }

    private void readData() {
        ByteBuffer dst = ByteBuffer.allocate(4096);
        Charset charset = StandardCharsets.UTF_8;

        try {
//            while (true) {
            FileOutputStream fileOutputStream = new FileOutputStream("data/server.txt");
            FileChannel outChannel = fileOutputStream.getChannel();
            SocketChannel conn = listener.accept();
            System.out.println("创建的连接: " + conn);
            conn.configureBlocking(true);
            int nread = 0;
            for (int i = 0; i < 100; i++) {

                outChannel.transferFrom(conn, 0, 400);
            }
//                outChannel.force(true);
//                fileOutputStream.flush();


//                while (nread != -1) {
//                    try {
//                        nread = conn.read(dst);
////                        System.out.println(charset.decode(dst));
//                        //指针反转，到buffer头
//                        dst.flip();
//                        while (dst.hasRemaining()){
//                            outChannel.write(dst);
//                        }
//                        outChannel.force(true);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        nread = -1;
//                    }
//                    dst.rewind();
//                }
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

