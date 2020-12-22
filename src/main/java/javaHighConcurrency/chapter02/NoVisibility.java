package javaHighConcurrency.chapter02;
/*
 * @Author: ljf
 * @Date: 2020-11-29 16:42:21 
 * @LastEditTime: 2020-11-29 17:07:19
 * @Description:
 */

public class NoVisibility {

    private static volatile boolean ready;
    private static int number;

    // 静态内部类只能访问外部类的静态属性和方法
    private static class ReadyThread extends Thread {
        public void run() {
            while (!ready)
                ;
            System.out.println(number);
        }
    }

    public static void main(String[] arg) throws InterruptedException {
        new ReadyThread().start();
        Thread.sleep(1000);
        System.out.println("hello");
        number = 42;
        ready = true;
        Thread.sleep(1000);
    }
}
