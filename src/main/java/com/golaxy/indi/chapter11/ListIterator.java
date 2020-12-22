package com.golaxy.indi.chapter11;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author ：ljf
 * @date ：2020/11/15 17:58
 * @description：列表容器
 * @modified By：
 * @version: $ 1.0
 */
interface Iterator<E> {
    //是否有下一个元素
    boolean hasNext();

    E next();
}

public class ListIterator<E> {
    private int size;
    private Object[] elementData;

    public ListIterator(int size) {
        this.size = size;
        this.elementData = new Object[size];
    }

    private class Itr implements Iterator<E> {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        /**
         * 获取下一个元素，判断是否有下一个元素
         * 多线程中局部变量替换全局变量。
         * 将全局变量的加载，更新，存回。改为程序栈元素中的更新将会加速很多
         *
         * @return
         */
        @Override
        public E next() {
            int i = cursor;

            if (i > size) {
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return (E) elementData[i];
        }



        @Override
        public String toString() {
            return Arrays.toString(elementData) + "Itr()";
        }
    }

    public void add(int index, E element) {
        if (index < size) {
            elementData[index] = element;
        }
    }

    public Iterator iterator(){
        return new Itr();
    }

    public static void main(String[] args) {
        ListIterator<Integer> list = new ListIterator<>(3);
        list.add(0,0);
        list.add(1,1);
        list.add(2,2);

        Iterator iterator = list.iterator();
        System.out.println(iterator);

        while (iterator.hasNext())
            System.out.println(iterator.next());
    }
}
