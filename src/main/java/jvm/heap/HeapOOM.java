package jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljf
 * @date: 2021/1/6 23:02
 * @description: 验证java堆溢出。
 * 保证GC ROOTs到这些对象有可达路径
 * @modified By:
 * @version: $ 1.0
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }
}
