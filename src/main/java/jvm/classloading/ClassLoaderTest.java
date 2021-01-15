package jvm.classloading;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: ljf
 * @date: 2021/1/15 7:56
 * @description: 类加载器和类本身共同唯一确立其在java虚拟机中的唯一性
 * 不同类加载器记载同一个class文件，不相等：equals，isAssignableFrom和isInstance方法
 * @modified By:
 * @version: $ 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                Class<?> clazz = null;
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    System.out.println("loading class file: " + fileName);
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        clazz =  super.loadClass(fileName);
                        return clazz;
                    }
                    byte[] bytes = new byte[inputStream.available()];
                    int read = inputStream.read(bytes);
                    clazz =  defineClass(fileName, bytes, 0, bytes.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return clazz;
            }
        };

        Object obj = myLoader.loadClass("jvm.classloading.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj instanceof jvm.classloading.ClassLoaderTest);
    }
}
