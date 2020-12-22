package com.golaxy.indi.chapter07;

import static net.mindview.util.Print.*;

/**
 * @author ：ljf
 * @date ：2020/11/1 20:53
 * @description：重写private方法
 * @modified By：
 * @version: $ 1.0
 */
class WithFinals{
    private final void f(){
        print("WithFinals.f()");
    }

    private void g(){
        print("WithFinals.g()");
    }
}
public class OverridingPrivate extends WithFinals {
    private final void f(){
        print("OverridingPrivate.g()");
    }

    public static void main(String[] args) {
        OverridingPrivate overridingPrivate = new OverridingPrivate();
        overridingPrivate.f();
    }
}
