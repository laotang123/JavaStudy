package com.golaxy.indi.chapter11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author ：ljf
 * @date ：2020/11/15 17:07
 * @description：集合类的创建
 * @modified By：
 * @version: $ 1.0
 */
public class SimpleCollection {
    public static void main(String[] args) {
        Collection<Integer> c = new HashSet<>();
//        Collection<Integer> c = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            c.add(i);
        }

        for (Integer integer : c) {
            System.out.print(integer + " ");
        }
    }
}
