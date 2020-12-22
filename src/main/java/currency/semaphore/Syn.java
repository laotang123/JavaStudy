package currency.semaphore;

import lombok.Synchronized;

/**
 * @author: ljf
 * @date: 2020/12/21 23:14
 * @description: 代码模拟信号量中的P操作和V操作
 * PV操作是原语操作
 * @modified By:
 * @version: $ 1.0
 */
public class Syn {
    int count = 0;

    public Syn() {
    }



    public Syn(int count) {
        this.count = count;
    }

    /**
     * synchronized 实现wait是一个原语，不可中断
     */
    public synchronized void P() {
        count--; //如果count为负值，那么abs(count)就是阻塞的线程数量
        if (count < 0) { //阻塞
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void V() {
        if (count++ <= 0) {
            this.notify();
        }
    }

}
