package corejava.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: ljf
 * @date: 2021/1/4 9:27
 * @description: nio通过阻塞监听事件，实现事件的非阻塞处理
 * @modified By：
 * @version: $ 1.0
 */
public class SelectorSingleThread {
    public static void main(String[] args) throws IOException {
        String demoText = "this is a demo string";
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //监听地址和端口
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //selector监听channel中的accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //读缓冲
        ByteBuffer buffer = ByteBuffer.allocate(256);

        while (true) {
            int readCount = selector.select();

            if (readCount <= 0) {
                break;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                int interestOps = selectionKey.interestOps();
                System.out.println(interestOps);

                //处理客户端链接
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }

                //处理客户端读事件
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.read(buffer);
                    if(new String(buffer.array(), StandardCharsets.UTF_8).equals(demoText)){
                        socketChannel.close();
                        System.out.println("Not accept client message anymore");
                    }

                    buffer.flip();
                    socketChannel.write(buffer);//写回
                    buffer.clear();//清除缓存
                }
                iterator.remove();
            }
        }

        serverSocketChannel.close();
    }
}
