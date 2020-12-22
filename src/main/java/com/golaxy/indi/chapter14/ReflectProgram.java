package com.golaxy.indi.chapter14;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2020/11/24 22:13
 * @description: 反射编程，获取接口，抽象类的方法？
 * @modified By:
 * @version: $ 1.0
 */
interface HasLeg {
    int legLength = 100;
    default void run() {
        System.out.println("running...");
    }

    default int getLegLength(){
        return legLength;
    }

}

abstract class Person {
    protected int height;
    protected double weight;

    public Person(int height,double weight){
        this.height = height;
        this.weight = weight;
    }
    public int getHeight(){
        return height;
    }

    public double getWeight(){
        return weight;
    }

    public abstract void setHeight(int height);
    public abstract void setWeight(double weight);
}

class Child extends Person implements HasLeg{

    public Child(int height, double weight) {
        super(height, weight);
    }

    @Override
    public void setHeight(int height) {
        this.height = height;

    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Child{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }
}

class Tmp{
    public int age = 10;
    public int num = 5;
}
public class ReflectProgram {
    public static void main(String[] args) {
        Child child = new Child(100, 40.5);
        child.setHeight(120);
        System.out.println(child);

        Tmp tmp = new Tmp();
        Map<String, Field> fieldList = ObjectUtil.getFieldList(child);
        System.out.println(child.getClass().getSuperclass().getDeclaredFields().length);
        fieldList.entrySet().forEach(System.out::println);
        Field[] declaredFields = child.getClass().getInterfaces()[0].getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field.getModifiers());
            System.out.println(Modifier.isStatic(field.getModifiers()));
            System.out.println(Modifier.isTransient(field.getModifiers()));
            System.out.println(field.getName());
        }
    }
}
