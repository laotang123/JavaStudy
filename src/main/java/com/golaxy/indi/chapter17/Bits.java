package com.golaxy.indi.chapter17;

import java.util.BitSet;

/*
 * @Author: ljf
 * @Date: 2020-11-29 08:25:18
 * @LastEditTime: 2020-11-29 08:40:07
 * @Description: java中bitSet操作
 */
public class Bits {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(128);
        bitSet.set(1);
        bitSet.set(3);

        System.out.println(bitSet);
        System.out.println(bitSet.get(1));
        System.out.println(bitSet);

    }
}
