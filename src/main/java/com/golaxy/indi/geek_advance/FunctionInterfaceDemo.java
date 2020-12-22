package com.golaxy.indi.geek_advance;

/**
 * @author: ljf
 * @date: 2020/11/19 20:20
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
@FunctionalInterface
interface Circle {
    //只支持一个抽象函数
    int calculate(int x);

    default double area(int y) {
        return 3.14 * y * y;
    }
}

@FunctionalInterface
interface Rectangle {
    int calculate(int height, int width);
}

public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        Circle circle = x -> x * x;
        Rectangle rectangle = (h, w) -> h * w;
        System.out.println(circle.calculate(3));
        System.out.println(circle.area(4));
        System.out.println("the area of rectangle: " + rectangle.calculate(4, 5));

        new Thread(() -> System.out.println("hello")).start();

    }
}
