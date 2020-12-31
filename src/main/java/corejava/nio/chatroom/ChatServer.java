package corejava.nio.chatroom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
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

            if (readyChannelCount == 0) {
                continue;
            }

            if (!selector.isOpen()) {
                this.close();
                return;
            }

            //TODO: 函数功能单一化，方便复用
            //处理accept事件和read事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            SelectionKey selectionKey;
            while (keyIterator.hasNext()) {
                selectionKey = keyIterator.next();
                this.handleSelectionKey(selectionKey);
                keyIterator.remove();
            }
        }
    }

    /**
     * 根据selectionKey类型触发相应的操作
     *
     * @param selectionKey：包含accept和read操作
     */
    private void handleSelectionKey(SelectionKey selectionKey) {
        if (selectionKey.isValid()) {
            if (selectionKey.isAcceptable()) {
                this.handleAcceptableKey(selectionKey);
            } else if (selectionKey.isReadable()) {
                this.handleReadableKey(selectionKey);
            } else {
                System.out.println("---some things wrong---");
            }
        }
    }

    /**
     * 读入数据
     */
    private void handleReadableKey(SelectionKey selectionKey) {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes;

        try {
            readBytes = socketChannel.read(readBuffer);
        } catch (IOException e) {
            //发生异常，断开连接
            this.handleClientDisconnect(socketChannel, selectionKey);
            e.printStackTrace();
            return;
        }

        if (readBytes > 0) {
            System.out.println(new String(readBuffer.array(), StandardCharsets.UTF_8));
        } else if (readBytes < 0) {
            //异常情况，断开连接
            this.handleClientDisconnect(socketChannel, selectionKey);
        }

    }

    private void handleClientDisconnect(SocketChannel socketChannel, SelectionKey selectionKey) {
        if (selectionKey != null) {
            selectionKey.cancel();
        }
        try {
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //服务端移除socketChannel
        socketChannelSet.remove(socketChannel);
    }

    /**
     * 处理accept操作的链接
     */
    private void handleAcceptableKey(SelectionKey selectionKey) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

        SocketChannel socketChannel = null;
        //接收新连接
        try {
            socketChannel = serverSocketChannel.accept();//非阻塞模式，可能返回null
            socketChannel.configureBlocking(false);

            //新连接socket注册到selector，监听读就绪事件
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            //关闭selectionKey
            this.closeSelectionKey(selectionKey);
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            e.printStackTrace();
        }

        if (socketChannel == null) {
            return;
        }

        System.out.println(getSocketIpAndPort(socketChannel) + "is online");
        socketChannelSet.add(socketChannel);
    }

    private String getSocketIpAndPort(SocketChannel socketChannel) {
        Socket socket = socketChannel.socket();
        return socket.getInetAddress().getHostName() + ": " + socket.getPort();
    }

//    private void sendConnectedInfoToAll(SocketChannel socketChannel) {
//
//    }
//
//    private void sendOnlineInfoToNew(SocketChannel socketChannel) {
//    }

    private void closeSelectionKey(SelectionKey selectionKey) {
        if (selectionKey != null) {
            selectionKey.cancel();
            if (selectionKey.channel() != null) {
                try {
                    selectionKey.channel().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void close() {
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
