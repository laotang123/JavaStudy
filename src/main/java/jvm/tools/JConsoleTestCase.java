package jvm.tools;


import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljf
 * @date: 2021/1/10 21:40
 * @description: JConsole 命令行调用
 * VM args: -Xms100m -Xmx100m -XX:+UseSerialGC
 * @modified By:
 * @version: $ 1.0
 */
public class JConsoleTestCase {
    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //控制填充堆的速度
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
        System.out.println("system gc over!");
        Thread.sleep(1000000);
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
