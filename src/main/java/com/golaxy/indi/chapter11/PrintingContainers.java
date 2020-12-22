package com.golaxy.indi.chapter11;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * @author ：ljf
 * @date ：2020/11/15 17:39
 * @description：查看容器的不同打印方式
 * @modified By：
 * @version: $ 1.0
 */
public class PrintingContainers {
    static void fill(Collection<String> collection) {
        collection.add("dog");
        collection.add("cat");
        collection.add("rat");

//        return collection;
    }

    public static void main(String[] args) {
        HashSet<String> hashSet = new HashSet<>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        fill(hashSet);
        fill(linkedHashSet);
        fill(treeSet);
        print(hashSet);
        print(linkedHashSet);
        print(treeSet);
        ArrayList<Integer> list = new ArrayList<>();
        list.iterator();


    }


}
