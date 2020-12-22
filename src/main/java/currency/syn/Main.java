package currency.syn;

import currency.semaphore.Syn;

/**
 * @author: ljf
 * @date: 2020/12/22 6:32
 * @description: 两个线程交替打印输出值
 * @modified By:
 * @version: $ 1.0
 */
public class Main {
    private static final Syn SYN = new Syn(1);
    private static final String PATTERN_OUT = "thread name: %s, current i: %s\n";


    public static class Process1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                SYN.P();
                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                SYN.V();
            }
        }
    }


    public static class Process2 implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                SYN.P();
                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                SYN.V();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 此时线程1和线程2并不一定是交替执行的
         */
        Thread t1 = new Thread(new Process1());
        Thread t2 = new Thread(new Process2());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
