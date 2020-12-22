package com.golaxy.indi.chapter09;

/**
 * @author ：ljf
 * @date ：2020/11/8 21:13
 * @description：
 * @modified By：
 * @version: $ 1.0
 */
public class Demo implements Child1,Child2 {
    @Override
    public void f1() {
        System.out.println("Parent.f1()");
    }

    @Override
    public void f() {
        System.out.println("Child.f()");
    }


    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.f();
    }
}
