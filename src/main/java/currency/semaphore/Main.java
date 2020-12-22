package currency.semaphore;

/**
 * @author: ljf
 * @date: 2020/12/22 21:57
 * @description: 信号量实现同步的相关测试
 * 辨别线程并发问题是同步还是互斥
 * @modified By:
 * @version: $ 1.0
 */
public class Main {
    private static final IntSemaphore s1 = new IntSemaphore(0);
    private static final IntSemaphore s2 = new IntSemaphore(0);

    //交替执行应该有通知机制
    public static void process1() {
        for (int i = 0; i < 10; i+=2) {
            s2.V();
            System.out.println(Thread.currentThread().getName() + "\t" + i);
            s1.P();
        }
    }

    public static void process2(){
        for (int i = 1; i < 10; i+=2) {
            s1.V();
            System.out.println(Thread.currentThread().getName() + "\t" + i);
            s2.P();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(Main::process1);
        Thread t2 = new Thread(Main::process2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
