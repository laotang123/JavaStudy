package com.golaxy.indi.chapter15;

import net.mindview.util.Generator;

/**
 * @author: ljf
 * @date: 2020/11/25 20:39
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public class BasicGenerator<T> implements Generator<T> {
    private final Class<T> type;

    public BasicGenerator(Class<T> type){
        this.type = type;
    }
    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<>(type);
    }


}
