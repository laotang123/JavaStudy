package com.golaxy.indi.chapter14;

import static net.mindview.util.Print.print;

/**
 * @author: ljf
 * @date: 2020/11/23 22:51
 * @description: 水果商店
 * @modified By:
 * @version: $ 1.0
 */
class Candy{
    static {
        print("loading candy...");
    }
}

class Gum{
    static {
        print("loading Gun...");
    }
}

class Cookie{
    static {
        print("loading Cookie...");
    }
}
public class SweetShop {
    public static void main(String[] args) {
        System.out.println("hello, world");
        new Candy();

        try {
           Candy candy = (Candy) Class.forName("com.golaxy.indi.chapter14.Candy").newInstance();
            System.out.println(candy);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        Cookie cookie = new Cookie();
        System.out.println(cookie.getClass().getCanonicalName());
        System.out.println(cookie.getClass().getName());
    }
}
