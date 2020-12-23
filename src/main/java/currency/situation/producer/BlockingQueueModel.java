package currency.situation.producer;

/**
 * @author: ljf
 * @date: 2020/12/23 7:58
 * @description: 基于阻塞队列实现生产者消费者
 * @modified By:
 * @version: $ 1.0
 */
public class BlockingQueueModel implements Model{
    @Override
    public Runnable newRunnableConsumer() {
        return null;
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ConsumerImpl();
//        return null;
    }

    private class ConsumerImpl extends AbstractConsumer{

        @Override
        public void consumer() throws InterruptedException {

        }
    }
}
