package com.golaxy.indi.chapter11;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author ：ljf
 * @date ：2020/11/15 21:27
 * @description：迭代器模式访问
 * @modified By：
 * @version: $ 1.0
 */
public class SimpleIteration {
    public static void main(String[] args) {
        ArrayList<Pet> pets = Pets.arrayList(12);

        System.out.println(pets);
        Iterator<Pet> iterator = pets.iterator();
        for (int i = 0; i < 10; i++) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(pets);
    }
}
