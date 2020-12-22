package com.golaxy.indi.chapter17;
/*
 * @Author: ljf
 * @Date: 2020-11-28 21:33:04
 * @LastEditTime: 2020-11-28 21:35:13
 * @Description: 
 */

public class StringHashDemo {
    public static void main(String[] args) {
        String[] hellos = "hello,hello".split(",");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
    }
}
