package jvm.classloading;

/**
 * @author: ljf
 * @date: 2021/1/15 10:26
 * @description: 类加载实例
 * @modified By：
 * @version: $ 1.0
 */
public class TestA {
    public void hello() {
        System.out.println("TestA: " + this.getClass().getClassLoader());
        TestB testB = new TestB();
        testB.hello();
    }

    public static void main(String[] args) {
        TestA testA = new TestA();
        testA.hello();
    }
}
