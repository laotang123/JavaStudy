package com.golaxy.indi.chapter11;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.*;

/**
 * @author ：ljf
 * @date ：2020/11/15 20:29
 * @description：列表的相关特性
 * @modified By：
 * @version: $ 1.0
 */
public class ListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(18);
        ArrayList<Pet> pets = Pets.arrayList(7);
        System.out.println("1: " + pets);

        //子集
        List<Pet> subList = pets.subList(1, 4);
        System.out.println("sub list of pets: " + subList);

        System.out.println(pets.containsAll(subList));
        Collections.sort(subList);
        System.out.println("sorted list: " + subList);

        pets.removeAll(subList);
        System.out.println(pets);
    }
}
