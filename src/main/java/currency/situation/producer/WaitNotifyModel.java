package currency.situation.producer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ljf
 * @date: 2020/12/24 7:25
 * @description: wait notify实现生产者消费者问题
 * 自定义缓冲区，原子增长的increaseTask
 * @modified By:
 * @version: $ 1.0
 */
public class WaitNotifyModel implements Model {
    public final AtomicInteger increaseNumber = new AtomicInteger();
    public final Queue<Task> buffer = new LinkedList<>();
    public final Object bufferLock = new Object();
    public final int capacity;

    public WaitNotifyModel(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer {


        @Override
        /*
        消费者方法：如果容量为空要进行阻塞 wait，放弃锁
         */
        public void consumer() throws InterruptedException {
            synchronized (bufferLock) {
                //注意这里是while不是if
                while (buffer.size() == 0) {
                    bufferLock.wait();
                }

                Task task = buffer.poll();
                //模拟服务执行操作
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("buffer capacity: " + buffer.size() + " consumer: " + task.number);
                //唤醒阻塞的生产者
                bufferLock.notifyAll();
            }
        }
    }

    private class ProducerImpl extends AbstractProducer {

        /**
         * 生产者方法，缓冲区满时，要阻塞该方法
         */
        @Override
        public void produce() throws InterruptedException {
            //模拟用户的不定期请求
            Thread.sleep((long) (Math.random() * 1000));
            synchronized (bufferLock) {
                while (buffer.size() == capacity) {
                    bufferLock.wait();
                }

                Task task = new Task(increaseNumber.getAndIncrement());
                buffer.offer(task);
                System.out.println("buffer capacity: " + buffer.size() + " produce: " + task.number);
                bufferLock.notifyAll();//唤醒消费者
            }
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public static void main(String[] args) {
        WaitNotifyModel waitNotifyModel = new WaitNotifyModel(3);
        for (int i = 0; i < 2; i++) {
            new Thread(waitNotifyModel.newRunnableProducer()).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread((waitNotifyModel.newRunnableConsumer())).start();
        }
    }
}
