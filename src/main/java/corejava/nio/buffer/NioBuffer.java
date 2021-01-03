package corejava.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: ljf
 * @date: 2021/1/3 8:57
 * @description: nio 的buffer
 * 设置48字节的缓冲区，循环读出，按照字节进行打印
 * @modified By：
 * @version: $ 1.0
 */
public class NioBuffer {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("data/a.txt", "rw");
        FileChannel inChannel = randomAccessFile.getChannel();

//        ByteBuffer readBuffer = ByteBuffer.allocate(48);
        ByteBuffer readBuffer = ByteBuffer.allocateDirect(48);
        int readCount = inChannel.read(readBuffer);

//        System.out.println("read count: " + readCount);
//        System.out.println(new String(readBuffer.array(), StandardCharsets.UTF_8));
        while (readCount != -1) {
            readBuffer.flip();//变为读模式，position=0，limit为刚写数据的末尾

            while (readBuffer.hasRemaining()) {
                System.out.println(readBuffer.get());
            }

            //mark记录某一时刻position的值，reset会将现有的position恢复到之前备份旧position值得mark，类似回退的功能
//            readBuffer.mark();
//            readBuffer.reset();

            //清除缓存，继续读入数据到buffer
            readBuffer.clear();
            readCount = inChannel.read(readBuffer);
        }
    }
}
