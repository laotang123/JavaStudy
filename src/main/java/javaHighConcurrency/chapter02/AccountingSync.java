package javaHighConcurrency.chapter02;

/*
 * @Author: ljf
 * @Date: 2020-11-29 17:14:11
 * @LastEditTime: 2020-11-29 17:30:47
 * @Description: synchronized锁的使用，类锁和静态方法锁。实例锁和非静态方法锁
 */
public class AccountingSync implements Runnable {
    private static int sum = 0;
    private static AccountingSync instance = new AccountingSync();

    public static synchronized void increase() {
        // synchronized (instance) {
        sum++;
        // }

    }

    @Override
    public void run() {
        // 将sum进行累加
        for (int i = 0; i < 1000_000; i++) {

            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingSync());
        Thread t2 = new Thread(new AccountingSync());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(sum);

    }

}
