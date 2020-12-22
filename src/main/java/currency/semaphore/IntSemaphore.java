package currency.semaphore;

/**
 * @author: ljf
 * @date: 2020/12/22 21:51
 * @description: 整形信号量
 * 阻塞有两种实现
 * CAS循环询问信号量 mutex，注意可见性volatile
 * 阻塞队列挂起
 * @modified By:
 * @version: $ 1.0
 */
public class IntSemaphore {
    private volatile int mutex = 1;
    private static final Object lockP = new Object();
    private static final Object lockV = new Object();

    public IntSemaphore() {
    }

    public IntSemaphore(int mutex) {
        this.mutex = mutex;
    }

    //保证操作的原子性，但是要保证P和V锁的独立性
    public void P() {
//        synchronized (lockP) {
        while (mutex <= 0) {
        }
        mutex--;
//        }
    }

    public void V() {
//        synchronized (lockV) {

            mutex++;

//        }
    }
}
