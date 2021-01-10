package currency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ljf
 * @date: 2021/1/10 18:16
 * @description: Future相比
 * @modified By：
 * @version: $ 1.0
 */

public class FutureUpdate {
    private static final AtomicInteger counter = new AtomicInteger(0);

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " counter: " + counter.incrementAndGet());
            }
            return "current count: " + counter.get();
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int nThreads = 4;
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

        List<Future<String>> futureList = new ArrayList<>();

        //定义任务
        Task task = new Task();
        for (int i = 0; i < 10; i++) {
            //线程池提交任务，并获得异步future
            Future<String> future = executorService.submit(task);
            futureList.add(future);
            //future的get方法，阻塞获取结果
            Thread.sleep(200);
        }

        for (Future<String> future : futureList) {

            System.out.println(future.get());//阻塞在主线程中，等待future的结果返回。
        }

        executorService.shutdown();


    }
}
