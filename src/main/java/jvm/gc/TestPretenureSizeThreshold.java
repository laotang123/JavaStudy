package jvm.gc;

/**
 * @author: ljf
 * @date: 2021/1/9 19:46
 * @description: 大对象分配直接放到老年代，防止minor gc是高额的内存赋值开销
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 * @modified By:
 * @version: $ 1.0
 */
public class TestPretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] alloction;
        alloction = new byte[_1MB * 4];
    }
}
