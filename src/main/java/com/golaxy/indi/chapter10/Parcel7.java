package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/12 22:51
 * @description：匿名内部类
 * @modified By：
 * @version: $ 1.0
 */

public class Parcel7 {
    public Selector compacts(){
       return new Selector() {
           @Override
           public boolean end() {
               return false;
           }

           @Override
           public Object current() {
               return null;
           }

           @Override
           public void next() {

           }
       };
    }

    public static void main(String[] args) {
        Parcel7 parcel7 = new Parcel7();
        System.out.println(parcel7.compacts());
    }
}
