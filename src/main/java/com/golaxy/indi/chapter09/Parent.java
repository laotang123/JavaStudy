package com.golaxy.indi.chapter09;

/**
 * @author ：ljf
 * @date ：2020/11/8 21:11
 * @description：
 * @modified By：
 * @version: $ 1.0
 */
public interface Parent {
    void f1();
}

interface Child1 extends Parent{
    void f();
}

interface Child2 extends Parent{
    void f();
}
