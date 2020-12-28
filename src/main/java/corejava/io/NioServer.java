package corejava.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: ljf
 * @date: 2020/12/28 16:45
 * @description: 不同环境下的nio实现方式不一
 * @modified By：
 * @version: $ 1.0
 */
public class NioServer {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //绑定socket
            ssc.socket().bind(new InetSocketAddress("192.168.21.192", 8000));

            //设置非阻塞模式
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            //注册channel，指定感兴趣的事件为accept
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuff = ByteBuffer.allocate(1024);
            ByteBuffer writeBuff = ByteBuffer.allocate(128);
            writeBuff.put("received".getBytes());
            writeBuff.flip();

            System.out.println(new String(writeBuff.array()));
            //轮询访问就绪事件
            while (true) {
                int nRead = selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();

                    //accept事件
                    if(key.isAcceptable()){
                        //创建新的链接，设置channel只对读感兴趣
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                    }else if(key.isReadable()){
                        //可读
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        readBuff.clear();
                        socketChannel.read(readBuff);

                        readBuff.flip();
                        System.out.println("received: "+ new String(readBuff.array()));
                        key.interestOps(SelectionKey.OP_WRITE);
                    }else if(key.isWritable()){
                        writeBuff.rewind();
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        socketChannel.write(writeBuff);
                        key.interestOps(SelectionKey.OP_READ);
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
