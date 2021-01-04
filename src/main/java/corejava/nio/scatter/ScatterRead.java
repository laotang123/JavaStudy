package corejava.nio.scatter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: ljf
 * @date: 2021/1/4 8:59
 * @description: java nio的分散读
 * @modified By：
 * @version: $ 1.0
 */
public class ScatterRead {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("data/a.txt", "r");

        FileChannel inChannel = randomAccessFile.getChannel();

        ByteBuffer readBuffer1 = ByteBuffer.allocate(48);
        ByteBuffer readBuffer2 = ByteBuffer.allocate(48);

        ByteBuffer[] buffers = {readBuffer1, readBuffer2};

        inChannel.read(buffers);

        System.out.println(inChannel.size());
        System.out.println(inChannel.position());

        System.out.println(new String(readBuffer1.array(), StandardCharsets.UTF_8));
        System.out.println(new String(readBuffer2.array(), StandardCharsets.UTF_8));

        //将buffer数组中的每一个buffer反转，变为读模式
        for (ByteBuffer buffer : buffers) {
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.print(buffer.get() + " ");
            }
            System.out.println();
        }
    }
}
