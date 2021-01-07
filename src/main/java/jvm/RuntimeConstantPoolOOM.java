package jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ljf
 * @date: 2021/1/7 21:30
 * @description: 自jdk1.7以后，字符创常量池由方法区的永久代 移入到堆中
 * VM args: -Xms3m -Xmx3m
 * TODO：堆溢出暂未验证出结果
 * @modified By:
 * @version: $ 1.0
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //使用Set对象保持常量池引用，防止Full GC回收常量池行为
//        Set<String> set = new HashSet<>();
//
//        long i = 0;
//        while (true) {
//            String s = RuntimeConstantPoolOOM.class.getName() + i;
//            //intern，如果字符创已经在常量池中则直接取出，如果没有则放入常量池并返回他的引用
//            set.add(s.intern());
//        }
        //计算机软件 字符串首次出现而且现在字符串常量池现在是放在堆中，所以s.intern是直接取出
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1);
        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s1.intern()));

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(System.identityHashCode(s2));
        System.out.println(System.identityHashCode(s2.intern()));
        System.out.println(s2.intern() == s2);


        System.out.println(System.identityHashCode(new StringBuilder("java").toString()));
        System.out.println(System.identityHashCode(new StringBuilder("java").toString()));
    }
}
