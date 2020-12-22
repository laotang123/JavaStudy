package com.golaxy.indi.chapter15;

import net.mindview.util.Generator;

import java.util.EnumSet;

/**
 * @author: ljf
 * @date: 2020/11/25 20:58
 * @description: 对象创建计数
 * @modified By:
 * @version: $ 1.0
 */
class CountedObject{
    public static long counter = 0;

    private final long id = counter++;

    public long getId(){
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject{" +
                "id=" + id +
                '}';
    }
}

public class BasicGeneratorDemo {
    public static void main(String[] args) {
        Generator<CountedObject> generator = BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 10; i++) {
            System.out.println(generator.next());
        }

        EnumSet<Color> colors = EnumSet.of(Color.RED, Color.YELLOW);
        System.out.println(colors);
        System.out.println(colors.clone());
    }
}
