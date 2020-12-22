package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/14 20:14
 * @description：内部类实现多继承
 * @modified By：
 * @version: $ 1.0
 */
class A {
    public void f(){
        System.out.println("a.f()");
    }
}

abstract class B {
    public abstract int getI();
}

public class D extends A {
    private int i = 0;
    B makeB() {
        return new B() {
            @Override
            public int getI() {
                return i;
            }

            @Override
            public String toString() {
                i++;
                return "hello,world";
            }

        };
    }

    public static void main(String[] args) {
        D d = new D();
        d.f();
        B b = d.makeB();
        System.out.println(b);
        System.out.println(b.getI());
    }
}
