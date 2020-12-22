package currency.situation.alternate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ljf
 * @date: 2020/12/22 19:59
 * @description: 使用可重入锁的condition实现 两个线程交替执行
 * 线程之间需要通知，可以通过现有的Api，也可以使用线程可见的信号量mutex实现
 * @modified By:
 * @version: $ 1.0
 */
public class ReentrantLockCondition extends BaseSuite {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition1 = lock.newCondition();
    private static final Condition condition2 = lock.newCondition();

    public static void process1() {
        for (int i = 0; i < TEST_TIMES; i += 2) {
            try {
                lock.lock();
                //换成线程2
                condition2.signal();
//                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                //阻塞当前线程，等待线程2唤醒自己
                condition1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();//释放锁放到finally中
            }
        }
        //防止空留线程2 阻塞
        lock.lock();
        condition2.signal();
        lock.unlock();

    }

    public static void process2() {
        for (int i = 1; i < TEST_TIMES; i += 2) {

            try {
                lock.lock();
                //换成线程1
                condition1.signal();
//                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                //阻塞当前线程，等待线程1唤醒自己
                condition2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //防止空留线程1 阻塞
        lock.lock();
        condition1.signal();
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ReentrantLockCondition::process1);
        Thread t2 = new Thread(ReentrantLockCondition::process2);

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        //cost time: 470535
        System.out.println("cost time: " + (System.currentTimeMillis() - start));

    }
}
