package com.golaxy.indi.geek_blockingQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.WeakHashMap;

/**
 * @author: ljf
 * @date: 2020/11/21 20:55
 * @description: 弱引用hashMap内存满时GC会优先清理掉这块数据
 * @modified By:
 * @version: $ 1.0
 */
class Demo {
    @Override
    public String toString() {
        return "Demo{}";
    }

    @Override
    public void finalize() {
        System.out.println("finalize method is called");
    }
}

public class WeekHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        Map<Demo, String> whm = new WeakHashMap<>();
//        Map<Demo, String> hm = new HashMap<>();
        Demo d1 = new Demo();
        Demo d2 = new Demo();

        whm.put(d1,"d1");
        whm.put(d2,"d2");
        d1 = null;//没有对象在引用map的key时

        System.out.println(whm);

        System.gc();

        //主线程睡眠，等待gc结束
        Thread.sleep(1000);
        System.out.println(whm);
    }
}
