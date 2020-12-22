package com.golaxy.indi.chapter11;

import java.util.Iterator;

/**
 * @author: ljf
 * @date: 2020/11/22 22:58
 * @description: 实现Iterable接口
 * @modified By:
 * @version: $ 1.0
 */
public class IterableClass implements Iterable<String>{
    protected String[] words = "geek for geek, thinking in java".split(" ");
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }

            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    public static void main(String[] args) {
        for (String s : new IterableClass()) {
            System.out.println(s);
        }
    }
}
