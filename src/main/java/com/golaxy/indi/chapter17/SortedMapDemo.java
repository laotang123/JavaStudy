package com.golaxy.indi.chapter17;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * @Author: ljf
 * @Date: 2020-11-28 18:11:38
 * @LastEditTime: 2020-11-28 21:19:27
 * @Description: 保持key有序的treeMap
 */

//实现自定义的插入顺序且唯一的Map
class SortedUniqueMap extends AbstractMap<Integer, String> {
    private int size;

    private static String[] chars = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    public SortedUniqueMap(int size) {
        if (size < 0)
            this.size = 0;
        this.size = size;
    }

    /**
     * 内部类实现map元素,静态内部类只能访问外部类的静态属性和方法。更节省内存
     */

    private static class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index) {
            this.index = index;
        }

        @Override
        public Integer getKey() {
            return index;
        }

        @Override
        public String getValue() {
            return chars[index % chars.length] + Integer.toString(index / chars.length);
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + index;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            return Integer.valueOf(index).equals(obj);
        }

    }

    /**
     * 关键是实现，entries的创建
     */
    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
        LinkedHashSet<Map.Entry<Integer, String>> entries = new LinkedHashSet<>();

        for (int i = 0; i < size; i++) {
            entries.add(new Entry(i));
        }
        return entries;
    }

}

public class SortedMapDemo {
    public static void main(String[] args) {

        TreeMap<Integer, String> sortedMap = new TreeMap<>(new SortedUniqueMap(27));

        Iterator<Map.Entry<Integer, String>> iterator = sortedMap.entrySet().iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "\t");
        }
        System.out.println();

        // TODO:

    }

}
