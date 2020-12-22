package currency.situation.producer;

/**
 * @author: ljf
 * @date: 2020/12/23 7:38
 * @description:
 * @modified By:
 * @version: $ 1.0
 */
public abstract class AbstractProducer implements Producer,Runnable{
    @Override
    public void run(){
        while (true){
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
