package currency.situation.producer;

/**
 * @author: ljf
 * @date: 2020/12/23 7:27
 * @description: 消费者抽象接口
 * @modified By:
 * @version: $ 1.0
 */
public interface Consumer {
    void consumer() throws InterruptedException;
}
