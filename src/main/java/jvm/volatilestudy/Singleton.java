package jvm.volatilestudy;

/**
 * @author: ljf
 * @date: 2021/1/4 6:35
 * @description: volatile修饰的赋值为原子性，防止指令重排
 * instance=new Singleton 正常指令顺序：
 *  1.分配对象内存空间
 *  2.初始化对象
 *  3.设置instance指向刚分配的内存地址
 * 但是有可能经过jvm和cpu的优化，将步骤2和3顺序颠倒。那么在判断if(instance==null)就会为false，
 * 但是指向一个未初始化的对象，从而出错
 * 单例模式：
 *  构造方法私有化
 *  双重检查机制
 *  volatile关键字
 *
 *  -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly  获取汇编代码
 * @modified By:
 * @version: $ 1.0
 */
public class Singleton {
    private static volatile Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());
    }
}
