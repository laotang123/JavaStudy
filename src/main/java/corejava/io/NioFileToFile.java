package corejava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author: ljf
 * @date: 2020/12/24 17:03
 * @description: 零拷贝，文件到文件之间的拷贝
 * @modified By：
 * @version: $ 1.0
 */
public class NioFileToFile {
        @SuppressWarnings("resource")
        public static void transferToDemo(String from, String to) throws IOException {
            FileChannel fromChannel = new RandomAccessFile(from, "rw").getChannel();
            FileChannel toChannel = new RandomAccessFile(to, "rw").getChannel();

            long position = 0;
            long count = fromChannel.size();

            fromChannel.transferTo(position, count, toChannel);

            fromChannel.close();
            toChannel.close();
        }

        @SuppressWarnings("resource")
        public static void transferFromDemo(String from, String to) throws IOException {
            FileChannel fromChannel = new FileInputStream(from).getChannel();
            FileChannel toChannel = new FileOutputStream(to).getChannel();

            long position = 0;
            long count = fromChannel.size();

            toChannel.transferFrom(fromChannel, position, count);

            fromChannel.close();
            toChannel.close();
        }

        public static void main(String[] args) throws IOException {
            String from = "data/a.txt";
            String to = "data/b.txt";
            // transferToDemo(from,to);
            transferFromDemo(from, to);
        }

}
