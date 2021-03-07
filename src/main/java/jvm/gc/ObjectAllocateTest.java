package jvm.gc;

/**
 * @author: ljf
 * @date: 2021/3/7 8:21
 * @description: 对象分配
 * 1.满足对象无法逃逸，标量替换分配在栈上
 * 2.优先分配在TLAB，线程本地分配缓存
 * 3.TLAB空间不足时，CAS操作分配在堆中
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB
 * @modified By:
 * @version: $ 1.0
 */
public class ObjectAllocateTest {
//    User u;
    static class User {
        int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    void alloc(int i) {
        new User(i, i + "name");
    }

    public static void main(String[] args) {
        ObjectAllocateTest objectAllocateTest = new ObjectAllocateTest();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            objectAllocateTest.alloc(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
