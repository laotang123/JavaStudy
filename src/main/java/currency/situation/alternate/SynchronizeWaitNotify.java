package currency.situation.alternate;

/**
 * @author: ljf
 * @date: 2020/12/22 7:46
 * @description: 使用同步方法的wait和notify来实现交替执行两个线程
 * 交替执行，必须是有两个线程之间相互通知的。使用notify来通知对方，自己
 * 执行完成之后就wait阻塞到队列中，释放cpu执行权
 *
 * 阻塞队列相比CAS优点是cpu让权，缺点是占用内存
 * @modified By:
 * @version: $ 1.0
 */
public class SynchronizeWaitNotify extends BaseSuite {

    /**
     * 打印奇数
     */
    public synchronized void print1() {
        for (int i = 0; i < TEST_TIMES; i += 2) {
            this.notify();
//            System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
            try {
                this.wait();
//                Thread.sleep(100);//防止打印速度过快
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();//唤醒所有未停止的进程
    }

    /**
     * 打印偶数
     */
    public synchronized void print2() {
        for (int i = 1; i < TEST_TIMES; i += 2) {
            this.notify();
//            System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
            try {
                this.wait();
//                Thread.sleep(100);//防止打印速度过快
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();//唤醒所有未停止的进程
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizeWaitNotify syn = new SynchronizeWaitNotify();
        //函数接口 Runnable
        Thread t1 = new Thread(syn::print1);
        Thread t2 = new Thread(syn::print2);

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        //cost time: 598114
        System.out.println("cost time: " + (System.currentTimeMillis() - start));
    }
}
