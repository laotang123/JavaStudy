package jvm.gc;

/**
 * @author: ljf
 * @date: 2021/1/9 19:46
 * @description: 测试内存分配
 * 流程：指定堆内存大小为20M，新生代为10M。且eden：survivor=8:1
 * 依次分配三个2M，到分配4M时，新生代可用内存(9M)不够。则通过空间担保进入老年代
 * 最终老年代有6M已分配内存，新生代有4M已分配内存
 * VM args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * @modified By:
 * @version: $ 1.0
 */
public class TestAllocation {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] alloction1, alloction2, alloction3, alloction4;

        alloction1 = new byte[_1MB * 2];
        alloction2 = new byte[_1MB * 2];
        alloction3 = new byte[_1MB * 2];
        alloction4 = new byte[_1MB * 4]; // 第一次出现minor gc 回收年轻代垃圾
    }
}
