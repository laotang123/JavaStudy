package jvm.classloading;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author: ljf
 * @date: 2021/1/14 9:38
 * @description: 被动引用  类初始化的三种情况
 * VM args: -XX:+TraceClassLoading
 * @modified By：
 * @version: $ 1.0
 */
class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}

class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLO_WORLD = "hello, world";
}

public class NotInitialization {
    public static void main(String[] args) {
        /**
         * 被动使用类字段演示一：
         * 通过自雷引用父类的静态字段，不会导致子类的初始化
         */

//        System.out.println(SubClass.value);


        /**
         * 被动使用类字段演示二：
         * 通过数组定义来引用类，不会触发此类的初始化
         */

//        SuperClass[] superClasses = new SuperClass[10];

        /**
         * 被动使用类字段演示三：
         * 常量在编译阶段会存入调用类的常量池，本质上没有直接医用定义常量的类，因此不会触发定义常量的类的初始化
         */

        System.out.println(ConstClass.HELLO_WORLD);

    }
}
