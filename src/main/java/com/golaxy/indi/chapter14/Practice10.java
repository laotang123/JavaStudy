package com.golaxy.indi.chapter14;

import java.net.Inet4Address;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2020/11/23 23:11
 * @description: 练习10，判断char数组是基本类型还是一个对象
 * @modified By:
 * @version: $ 1.0
 */
interface HasHair {
}

class Student implements HasHair {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

public class Practice10 {
    public static void main(String[] args) throws Exception {
        char[] chars = {'1', '2', '3', 'a'};
        System.out.println(chars.getClass().getCanonicalName());
        System.out.println(chars.getClass().getName());

        System.out.println(Arrays.toString(chars));
        System.out.println(int.class == Integer.TYPE);

        Student stu = (Student) Class.forName("com.golaxy.indi.chapter14.Student").newInstance();
        for (Class<?> anInterface : stu.getClass().getInterfaces()) {
            System.out.println(anInterface);
        }

    }
}
