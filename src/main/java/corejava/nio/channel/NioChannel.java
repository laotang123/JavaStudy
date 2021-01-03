package corejava.nio.channel;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ljf
 * @date: 2021/1/3 14:10
 * @description: nio channel相关api
 * @modified By：
 * @version: $ 1.0
 */
public class NioChannel {
    public static void main(String[] args) throws FileNotFoundException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("data/a.txt", "r");
        FileChannel inChannel = randomAccessFile.getChannel();

    }
}
