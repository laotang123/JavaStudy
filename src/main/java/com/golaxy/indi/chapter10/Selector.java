package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/12 22:17
 * @description：迭代器接口
 * @modified By：
 * @version: $ 1.0
 */
public interface Selector {
    boolean end();
    Object current();
    void next();
}
