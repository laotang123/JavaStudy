package com.golaxy.indi.geek_collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: ljf
 * @date: 2020/11/19 21:57
 * @description: 验证写时复制列表特性，add操作就会拷贝一份
 * 适合读多写少的场景。并发线程安全
 * @modified By:
 * @version: $ 1.0
 */
public class CopyOnWriteListDemo {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> stringList = new CopyOnWriteArrayList<>();

        Iterator<String> iterator = stringList.iterator();
        stringList.add("GFG"); //use ReentrantLock

        System.out.println("add before contains: ");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("add after contains: ");
        Iterator<String> iterator1 = stringList.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        System.out.println(stringList);

    }
}
