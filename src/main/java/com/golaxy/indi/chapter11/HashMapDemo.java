package com.golaxy.indi.chapter11;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ljf
 * @date ：2020/11/18 22:51
 * @description：
 * @modified By：
 * @version: $ 1.0
 */
public class HashMapDemo {
    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("dfas",4.);
        map.put("gad",1.);
        map.put("fgds",2.);
        map.put("eqwr",3.);
        for (String s : map.keySet()) {
            System.out.println(s.hashCode());
        }

       map.entrySet().stream().sorted(Map.Entry.comparingByKey())
               .forEach(System.out::println);
    }
}
