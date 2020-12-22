package com.golaxy.indi.chapter15;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2020/11/25 21:45
 * @description: java实现泛型使用的是类型擦除
 * @modified By:
 * @version: $ 1.0
 */

class HasF{
    public void f(){
        System.out.println("HasF.f()");
    }
}
class Manipulator<T extends HasF>{
    private T obj;
    public Manipulator(T t){
        this.obj = t;
    }

    public void manipulate(){
        System.out.println(obj.getClass().getName());
        obj.f();
    }
}
public class LostInformation {
    public static void main(String[] args) {
        Manipulator<HasF> hasFManipulator = new Manipulator<>(new HasF());
        hasFManipulator.manipulate();
        System.out.println( Arrays.toString(hasFManipulator.getClass().getTypeParameters()));
    }
}
