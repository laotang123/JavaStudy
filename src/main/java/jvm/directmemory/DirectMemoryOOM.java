package jvm.directmemory;

import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * @author: ljf
 * @date: 2021/1/7 22:26
 * @description: 直接内存溢出
 * 默认情况下直接内存的值与java堆的最大值一致。
 * VM args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * @modified By:
 * @version: $ 1.0
 */

class A {
    private A() {
    }

    private static final A instance = new A();
}

public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        System.out.println(unsafe);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
