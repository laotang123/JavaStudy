package corejava.nio.chatroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author: ljf
 * @date: 2020/12/31 9:59
 * @description: 客户端，当前只负责连接和写数据
 * @modified By：
 * @version: $ 1.0
 */
public class ChatClient {
    public static void main(String[] args) {
        Selector selector = null;
        SocketChannel socketChannel = null;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();

            //设置非阻塞
            socketChannel.configureBlocking(false);

            boolean connected = socketChannel.connect(new InetSocketAddress("127.0.0.1", 12345));

            while (socketChannel.finishConnect()) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("input message: ");
                String message = scanner.nextLine();

                byte[] messageBytes = message.getBytes();
                ByteBuffer writeBuffer = ByteBuffer.allocate(messageBytes.length);

                writeBuffer.put(messageBytes);
                writeBuffer.flip();//将position位置=0，limit=writed size
                socketChannel.write(writeBuffer);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
