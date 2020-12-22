package currency.situation.alternate;

/**
 * @author: ljf
 * @date: 2020/12/22 7:19
 * @description: 自定义信号量实现交替执行，CAS循环更消耗cpu
 * @modified By:
 * @version: $ 1.0
 */
public class UserDefineMutex extends BaseSuite {
    private static volatile int mutex = 0;

    public static class Process1 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < TEST_TIMES; i += 2) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //死循环阻塞，并没有停下前进的脚步，cpu在高速执行中
                while (mutex != 1) {
                }
                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                mutex = 0;
            }
        }
    }

    public static class Process2 implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i < TEST_TIMES; i += 2) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                //死循环阻塞
                while (mutex != 0) {
                }
                System.out.printf(PATTERN_OUT, Thread.currentThread().getName(), i);
                mutex = 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Process1());
        Thread t2 = new Thread(new Process2());

        long start = System.currentTimeMillis();
        t2.start();
        t1.start();

        t2.join();
        t1.join();
        //cost time: 3403
        System.out.println("cost time: " + (System.currentTimeMillis() - start));
    }
}
