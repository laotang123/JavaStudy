package corejava.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * @author: ljf
 * @date: 2021/1/10 16:09
 * @description: pipe，流式通道。
 * 写入数据通过sinkChannel，从sourceChannel中读出数据
 * 可跨线程写入和读出数据
 * @modified By：
 * @version: $ 1.0
 */
public class PipeDemo {
    private static final Pipe PIPE = createPipe();

    public static Pipe createPipe() {
        Pipe pipe = null;
        try {
            pipe = Pipe.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pipe;
    }

    private static class WriteRunnable implements Runnable {


        @Override
        public void run() {
            Pipe.SinkChannel sinkChannel = PIPE.sink();

            String data = "New String write to file..." + System.currentTimeMillis();
            //分配buffer内存
            ByteBuffer writeBuffer = ByteBuffer.allocate(48);
            writeBuffer.clear();

            writeBuffer.put(data.getBytes());

            //循环写入数据到sinkChannel
            writeBuffer.flip();
            while (writeBuffer.hasRemaining()) {
                try {
                    sinkChannel.write(writeBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ReadRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000); //等待写线程先启动
                Pipe.SourceChannel sourceChannel = PIPE.source();
                //数据读出到缓存当中
                ByteBuffer readBuffer = ByteBuffer.allocate(48);

                int readCount = sourceChannel.read(readBuffer);
                System.out.println("read Count: " + readCount);
                System.out.println("read content: " + new String(readBuffer.array(), StandardCharsets.UTF_8));

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
//        String data = "New String write to file..." + System.currentTimeMillis();
//        ByteBuffer buffer = ByteBuffer.allocate(4);
////        buffer.put(data.getBytes());
//
//        int offset = 0;
//        int remaining = data.length() - offset;
//        byte[] bytes = data.getBytes();
//
//        byte[] result = new byte[48];
//        int index = 0;
//        while (offset < data.length()) {
//            //每次写入4字节，
//            int length = Math.min(remaining, 4);
//            buffer.put(bytes, offset, length);
//            System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
//            for (byte b : buffer.array()) {
//                result[index++] = b;
//            }
//            buffer.clear();
//            offset += 4;
//        }
//
//        System.out.println(new String(result, StandardCharsets.UTF_8));
        Thread t1 = new Thread(new WriteRunnable());
        Thread t2 = new Thread(new ReadRunnable());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}
