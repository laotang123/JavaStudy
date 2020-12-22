package com.golaxy.indi.chapter08;

import static net.mindview.util.Print.*;

/**
 * @author ：ljf
 * @date ：2020/11/1 21:24
 * @description：风琴
 * @modified By：
 * @version: $ 1.0
 */
public class Wind extends Instrument {
    @Override
    public void play(Note n) {
        print("Mind.play()" + n);
    }
}
