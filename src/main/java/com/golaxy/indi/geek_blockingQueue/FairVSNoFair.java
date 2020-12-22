package com.golaxy.indi.geek_blockingQueue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ljf
 * @date: 2020/11/21 7:28
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
class FairLock implements Runnable {
    public static ReentrantLock fairLock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            fairLock.lock();
            System.out.println(Thread.currentThread().getName() + "线程获取锁");
            fairLock.unlock();
        }
    }
}

public class FairVSNoFair {
    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock, "thread_t1");
        Thread t2 = new Thread(fairLock, "thread_t2");
//        new Semaphore()
        t1.start();
        t2.start();


    }
}
