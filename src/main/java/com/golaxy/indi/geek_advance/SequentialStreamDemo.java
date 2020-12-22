package com.golaxy.indi.geek_advance;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author: ljf
 * @date: 2020/11/19 20:43
 * @description: 创建序列化流
 * @modified By:
 * @version: $ 1.0
 */
public class SequentialStreamDemo {
    /**
     * 使用泛型将迭代器转成序列化流，分离迭代器使用UnknownSize函数创建
     */
    public static <T> Stream<T> iteratorToSequentialStream(Iterator<T> itr) {
        Spliterator<T> tSpliterator = Spliterators.spliteratorUnknownSize(itr, Spliterator.NONNULL);

        return StreamSupport.stream(tSpliterator, false);
    }

    /**
     * lambda表达式实现
     */
    public static <T> Stream<T> useLambda(Iterator<T> itr) {
        Iterable<T> itb = () -> itr;

        return StreamSupport.stream(itb.spliterator(), false);
    }

    public static void println(String x){
        System.out.println(x);
    }
    public static void main(String[] args) {
        List<String> list = Arrays.asList("G", "E", "E", "K", "S");

        Stream<String> stringStream = iteratorToSequentialStream(list.iterator());
        System.out.println(stringStream.collect(Collectors.toList()));

        Stream<String> stringStream1 = useLambda(list.iterator());
        System.out.println(stringStream1.collect(Collectors.toList()));

        Consumer<String> consumer = System.out::println;
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.forEachRemaining(consumer);
    }
}
