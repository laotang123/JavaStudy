package com.golaxy.indi.chapter11;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: ljf
 * @date: 2020/11/22 21:47
 * @description: 练习题19
 * @modified By:
 * @version: $ 1.0
 */
public class Practice19 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("liujunfeng", 1);
        map.put("liuduo", 2);
        map.put("shaomeiqi", 3);

        System.out.println(map);
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Comparator.comparingInt(o -> o.getKey().length()))
                .forEach(o -> linkedHashMap.put(o.getKey(), o.getValue()));

//        linkedHashMap.forEach(System.out::printf);
        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry);
        }
    }
}
