/*
 * @Author: ljf
 * @Date: 2020-11-29 17:35:01
 * @LastEditTime: 2020-11-29 18:21:59
 * @Description: 多线程下的ArrayList，访问插入
 */
package javaHighConcurrency.chapter02;

import java.util.ArrayList;
import java.util.List;

public class ArrayMultiThread implements Runnable {
    private static final List<Integer> list = new ArrayList<>();

    public synchronized void add() {
        for (int i = 0; i < 1000_000; i++) {
            list.add(i);
        }

    }

    @Override
    public void run() {
        add();
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayMultiThread arrayMultiThread = new ArrayMultiThread();
        Thread t1 = new Thread(arrayMultiThread);
        Thread t2 = new Thread(arrayMultiThread);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(list.size());
    }
}
