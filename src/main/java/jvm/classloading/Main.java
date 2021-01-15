package jvm.classloading;

import java.lang.reflect.Method;

/**
 * @author: ljf
 * @date: 2021/1/15 11:05
 * @description: 测试自定义类加载器
 * @modified By：
 * @version: $ 1.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
        //这里取AppClassLoader的父加载器作为ExtClassLoader作为MyClassLoaderCustom的jdkClassLoader
        MyClassLoaderCustom myClassLoader = new MyClassLoaderCustom(Thread.currentThread().getContextClassLoader().getParent());

        myClassLoader.addClassAndName("jvm.classloading.TestA", "target/classes/jvm/classloading/TestA.class");
        myClassLoader.addClassAndName("jvm.classloading.TestB", "target/classes/jvm/classloading/TestB.class");

        Class<?> testAClass = myClassLoader.loadClass("jvm.classloading.TestA");
        Method mainMethod = testAClass.getDeclaredMethod("main", String[].class);

        mainMethod.invoke(null, new Object[]{args});


    }
}
