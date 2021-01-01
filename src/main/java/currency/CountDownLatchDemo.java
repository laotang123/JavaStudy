package currency;

import java.util.concurrent.CountDownLatch;

/**
 * @author: ljf
 * @date: 2021/1/1 10:33
 * @description: 基于AQS的主线程等待一定数量的操作，退出
 * @modified By：
 * @version: $ 1.0
 */
public class CountDownLatchDemo {
    private static final int COUNT = 3;
    private static final CountDownLatch latch = new CountDownLatch(COUNT);

    public static class InnerClass implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < COUNT; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();//如果count值已经为0，就不会再减了
                    System.out.println("latch count: " + latch.getCount());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InnerClass innerClass = new InnerClass();
        new Thread(innerClass).start();
//        new Thread(innerClass).start();

        latch.await();

        System.out.println("finish");
    }
}
