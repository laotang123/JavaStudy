package jvm.modifycodebyte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: ljf
 * @date: 2021/1/18 14:09
 * @description: 详解class文件结构
 * @modified By：
 * @version: $ 1.0
 */
public class DetailClassStructure {
    private static final int u4 = 4;
    private static final int u2 = 2;
    private static final int u1 = 1;
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 方法二：
     * byte[] to hex string
     * 一个字节为8位，4位表示16进制。故一个字节可以表示两个16进制的数
     */
    public static String bytesToHexFun(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }

        return new String(buf);
    }

    public static void main(String[] args) throws IOException {
        String classPath = "target/test-classes/Hello.class";
        File file = new File(classPath);
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        int readCount = fis.read(buffer);

//        System.out.println(new String(buffer, StandardCharsets.UTF_8));

        int offset = 0;
        int magic = ByteUtils.bytes2Int(buffer, offset, u4);
        byte[] magicBuffer = new byte[u4];
        System.arraycopy(buffer, offset, magicBuffer, 0, u4);
        String str = bytesToHexFun(magicBuffer);
        offset += u4;
        System.out.println("magic: " + magic);
        System.out.println("magic hex str: " + str);


        int minor_version = ByteUtils.bytes2Int(buffer, offset, u2);
        offset += u2;
        System.out.println("minor_version: " + minor_version);


        int major_version = ByteUtils.bytes2Int(buffer, offset, u2);
        offset += u2;
        System.out.println("major_version: " + major_version);


        int constant_pool_count = ByteUtils.bytes2Int(buffer, offset, u2);
        offset += u2;
        System.out.println("constant_pool_count: " + constant_pool_count);
    }
}
