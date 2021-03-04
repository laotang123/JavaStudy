package jvm.heap;

import com.sun.org.apache.bcel.internal.util.ClassLoader;
import indi.ljf.jvm.agent.ObjectSizeAgent;

/**
 * @author: ljf
 * @date: 2021/3/4 13:10
 * @description: 测试object对象占用内存字节数
 * vm options: -javaagent:lib/ObjectSize.jar
 *
 * * -XX:+UseCompressedClassPointers 为4字节 不开启为8字节
 * * 引用类型：-XX:+UseCompressedOops 为4字节 不开启为8字节 Oops: Ordinary Object Pointers
 * @modified By：
 * @version: $ 1.0
 */
public class SizeOfAnObject {
    public static void main(String[] args) {
        System.out.println(ObjectSizeAgent.sizeOf(new Object()));
        System.out.println(ObjectSizeAgent.sizeOf(new int[] {}));
        System.out.println(ObjectSizeAgent.sizeOf(new P()));

    }

    private static class P {
        //8 _markword
        //4 _oop指针
        int id;         //4
        String name;    //4
        int age;        //4

        byte b1;        //1
        byte b2;        //1

        Object o;       //4
        byte b3;        //1

    }
}
