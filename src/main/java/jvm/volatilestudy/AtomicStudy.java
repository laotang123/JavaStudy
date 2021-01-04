package jvm.volatilestudy;

/**
 * @author: ljf
 * @date: 2021/1/3 22:52
 * @description: volatile能够解决赋值的正确性：防止指令重拍
 * volatile不能解决自增的原子性
 * @modified By：
 * @version: $ 1.0
 */
public class AtomicStudy {
    private static volatile int counter = 0;

    private static final int THREAD_COUNT = 20;

    private static void increase() {
        for (int i = 0; i < 10000; i++) {
            counter++;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(AtomicStudy::increase).start();
        }

        //如果还有工作线程，主线程就释放当前获取执行权力
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(counter);
    }
}
