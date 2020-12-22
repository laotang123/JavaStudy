package com.golaxy.indi.chapter08;

import static net.mindview.util.Print.*;

/**
 * @author ：ljf
 * @date ：2020/11/1 21:56
 * @description：构造器多态
 * @modified By：
 * @version: $ 1.0
 */
class Glyph {
    void draw() {
        print("Glyph.draw()");
    }

    Glyph() {
        print("Glyph() before draw()");
        draw();
        print("Glyph() after draw()");
    }
}

public class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        print("RoundGlyph.RoundGlyph(), radius: = " + radius);
    }

    @Override
    void draw() {
        print("RoundGlyph.draw(), radius: = " + radius);

    }

    public static void main(String[] args) {
        new RoundGlyph(5);
    }
}
