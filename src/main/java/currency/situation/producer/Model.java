package currency.situation.producer;

/**
 * @author: ljf
 * @date: 2020/12/23 7:41
 * @description: 抽象生产者消费者编程模型
 * @modified By:
 * @version: $ 1.0
 */
public interface Model {
    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();

}
