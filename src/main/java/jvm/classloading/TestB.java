package jvm.classloading;

/**
 * @author: ljf
 * @date: 2021/1/15 10:26
 * @description: 类加载实例
 * @modified By：
 * @version: $ 1.0
 */
public class TestB {

    public void hello() {
        System.out.println("TestB: " + this.getClass().getClassLoader());
    }
}
