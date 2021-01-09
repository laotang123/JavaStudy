package jvm.tools;

/**
 * @author: ljf
 * @date: 2021/1/10 7:15
 * @description: JHSDB jdk9版本
 * staticObj，instanceObj，localObj各自存放在哪里？
 * VM args: -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 * @modified By:
 * @version: $ 1.0
 */
public class JHSDBTestCase {
    private static class ObjectHolder{}

    static class Test{
        static ObjectHolder staticObj = new ObjectHolder();

        ObjectHolder instanceObj = new ObjectHolder();

        void foo(){
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }
    public static void main(String[] args) {
        Test test = new Test();

        test.foo();
    }
}
