package com.golaxy.indi.geek_blockingQueue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ljf
 * @date: 2020/11/21 6:54
 * @description: 可重入锁加入了可中断性
 * synchronized要么处于执行状态，要么处于等待状态
 * @modified By:
 * @version: $ 1.0
 */
class IntLock implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();

    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        //根据lock的值决定锁的顺序，形成死锁
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + currentThread.getId() + "：线程退出");
        }
    }
}

public class LockInterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        IntLock il1 = new IntLock(1);
        IntLock il2 = new IntLock(2);

        Thread t1 = new Thread(il1);
        Thread t2 = new Thread(il2);

        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();//中断其中一个线程，消除死锁

    }
}
