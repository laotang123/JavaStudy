package com.golaxy.indi.chapter11;

import java.util.ArrayList;

/**
 * @author ：ljf
 * @date ：2020/11/15 16:49
 * @description：ArrayList去除类型检测？ javaSE5
 * @modified By：
 * @version: $ 1.0
 */
class Apple{
    private static long counter;
    private final long id = counter++;
    public long getId(){
        return id;
    }
}
class Orange{}
public class ApplesAndOrangesWithoutGenerics {
    public static void main(String[] args) {
        ArrayList<Apple> list = new ArrayList<>();

        //填充数组
        for (int i = 0; i < 3; i++) {
            list.add(new Apple());
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getId());
        }
    }
}
