package currency.syn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ljf
 * @date: 2020/12/21 22:30
 * @description: 软件实现方法中的单标志法实现交替进入临界区
 * 访问临界资源的那部分代码称为临界区，一次仅允许一个进程使用的资源称为临界资源
 * 无锁实现交替打印，但是会浪费资源。时间片大于每次线程的执行体消耗的时间
 * @modified By:
 * @version: $ 1.0
 */
class PV {
    int mutex = 0;
}

public class SingleSignMethod {
        private static volatile int mutex = 0; //此处的volatile是为了mutex的线程可见性，如果去除，最先启动的线程会阻塞
//    private static PV pv = new PV(); // 类属性和类之间可见的？
    private static final String PATTERN_OUT = "thread name: %s, current i: %s\n";
    private static final int TEST_TIMES = 10;
    private static volatile int count = 0;

    public static class Process1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < TEST_TIMES; i++) {
                //如果不是交替执行，就会阻塞在这里，会造成资源浪费？，循环加锁
//                while (pv.mutex != 0) {
                while (mutex != 0) {
                }
                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                count++;
                mutex = 1;//这里就是解锁
            }
            System.out.println("count: " + count);
        }
    }

    public static class Process2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < TEST_TIMES; i++) {
//                while (pv.mutex != 1) {
                while (mutex != 1) {
                }
                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                count++;
//                pv.mutex = 0;
                mutex = 0;
            }
            //两个线程的count打印值是相邻的，代表交替执行两个线程
            System.out.println("count: " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Process1());
        Thread t2 = new Thread(new Process2());

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
