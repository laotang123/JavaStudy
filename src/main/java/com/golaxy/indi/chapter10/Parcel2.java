package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/10 22:43
 * @description：内部类实现
 * @modified By：
 * @version: $ 1.0
 */
public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination{
        private String label;

        Destination(String whereTo){
            this.label = whereTo;
        }

        String readLabel(){
            return label;
        }
    }

    public Destination to(String s){
        return new Destination(s);
    }
    public Contents contents(){
        return new Contents();
    }

    public void ship(String dest){
        Contents contents = contents();
        Destination destination = to(dest);
        System.out.println(destination.readLabel());
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        Contents contents = p.contents();
        System.out.println(contents);
        p.ship("Borneo");
    }
}
