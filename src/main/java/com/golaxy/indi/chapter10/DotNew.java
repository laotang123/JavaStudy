package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/12 22:32
 * @description：实例化内部类需要持有外部类的引用
 * @modified By：
 * @version: $ 1.0
 */
public class DotNew {
    public class Inner{

    }

    public static void main(String[] args) {
        DotNew dt = new DotNew();
        Inner inner = dt.new Inner();
        System.out.println(inner);
    }
}
