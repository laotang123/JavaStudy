package currency.situation.producer;

/**
 * @author: ljf
 * @date: 2020/12/23 7:29
 * @description: 抽象消费者，封装consumer接口和实现Runnable
 * @modified By:
 * @version: $ 1.0
 */
public abstract class AbstractConsumer implements Consumer,Runnable{
    @Override
    public void run(){
        while (true){
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
