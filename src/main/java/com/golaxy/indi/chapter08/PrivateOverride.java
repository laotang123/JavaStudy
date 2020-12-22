package com.golaxy.indi.chapter08;


import static net.mindview.util.Print.print;

/**
 * @author ：ljf
 * @date ：2020/11/1 21:40
 * @description：
 * @modified By：
 * @version: $ 1.0
 */
class Derived extends PrivateOverride{
    public void f(){
        print("public f()");
    }
}
public class PrivateOverride {
    private void f(){
        print("private f()");
    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
    }
}
