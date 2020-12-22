package com.golaxy.indi.chapter10;

import java.util.ArrayList;

/**
 * @author ：ljf
 * @date ：2020/11/12 22:18
 * @description：内部类实现迭代器
 * @modified By：
 * @version: $ 1.0
 */
public class Sequence {
    private Object items[];
    private int next = 0;

    public Sequence(int size) {
        this.items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length) {
            this.items[next++] = x;
        }
    }

    private class SequenceSelector implements Selector {
        private int i = 0;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) {
                i++;
            }
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }


    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);

        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.toString(i));
        }

        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }


    }
}
