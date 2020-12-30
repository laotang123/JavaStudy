package corejava.nio.chatroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: ljf
 * @date: 2020/12/30 22:59
 * @description: nio 实现聊天室，Windows使用select实现nio
 * linux使用epoll实现nio
 * 服务端：
 * 1.selector监听可读操作和accept连接操作
 * @modified By:
 * @version: $ 1.0
 */
public class ChatServer {
    private Selector selector;
    private final Set<SocketChannel> socketChannelSet = new HashSet<>(); //与客户端建立的socket集合

    public static void main(String[] args) {
        int serverPort = 12345;
        ChatServer chatServer = new ChatServer();
        chatServer.execute(serverPort);
    }

    private void execute(int serverPort) {
        try {
            selector = Selector.open();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//设置非阻塞
            serverSocketChannel.socket().bind(new InetSocketAddress(serverPort));

            //将channel绑定到selector上，服务端只对accept事件感兴趣
            //accept,read,write,connect
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            //select限时等待
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void start() {
        int readyChannelCount;
        while (true) {
            try {
                readyChannelCount = selector.select(1000);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            if(readyChannelCount == 0){
                continue;
            }

            if(!selector.isOpen()){
                this.close();
                return;
            }

            //TODO: 函数功能单一化，方便复用
            //处理accept事件和read事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            SelectionKey selectionKey;
            while (keyIterator.hasNext()){
                selectionKey = keyIterator.next();
                this.handleSelectionKey(selectionKey);
                keyIterator.remove();
            }
        }
    }

    /**
     * 根据selectionKey类型触发相应的操作
     * @param selectionKey：包含accept和read操作
     */
    private void handleSelectionKey(SelectionKey selectionKey) {

    }

    private void close() {
        if(selector!=null){
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
