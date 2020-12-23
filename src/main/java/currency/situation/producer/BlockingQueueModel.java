package currency.situation.producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ljf
 * @date: 2020/12/23 7:58
 * @description: 基于阻塞队列实现生产者消费者
 * @modified By:
 * @version: $ 1.0
 */
public class BlockingQueueModel implements Model {
    private final BlockingQueue<Task> queue;
    private final AtomicInteger increaseTaskNumber = new AtomicInteger(0);

    public BlockingQueueModel(int capacity) {
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ProducerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ConsumerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer {

        @Override
        public void consumer() throws InterruptedException {
            Thread.sleep(500 + (long) (Math.random() * 500));
            Task task = queue.take();
            //固定时间范围内的消费，模拟相对稳定服务器处理过程
            System.out.println("queue remain capacity: " + queue.remainingCapacity() + " consume: " + task.number);
        }
    }

    private class ProducerImpl extends AbstractProducer {

        @Override
        public void produce() throws InterruptedException {
            //不定期生产，模拟用户的随机请求
            Thread.sleep((long) (Math.random() * 1000));
            Task task = new Task(increaseTaskNumber.getAndIncrement());
            System.out.println("queue remain capacity: " + queue.remainingCapacity() + " produce: " + task.number);
            queue.put(task);
        }
    }

    public static void main(String[] args) {
        BlockingQueueModel queueModel = new BlockingQueueModel(3);
        //2个生产者，5个消费者
        for (int i = 0; i < 2; i++) {
            new Thread(queueModel.newRunnableProducer()).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(queueModel.newRunnableConsumer()).start();
        }
    }
}
