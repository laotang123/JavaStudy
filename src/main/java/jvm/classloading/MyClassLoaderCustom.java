package jvm.classloading;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ljf
 * @date: 2021/1/15 10:32
 * @description: 自定义类加载器
 * 重写findClass方法，同时打破双亲委派机制。不要让用户指定类向上查找
 * loadClass 重写打破双亲委派
 * findClass 从当前加载器中查找是否存在
 *
 * FIXME: 这里findClass这是将TestA的ClassLoader设置为自定义，TestB还是双亲委派到AppClassLoader来加载
 * @modified By：
 * @version: $ 1.0
 */
public class MyClassLoaderCustom extends ClassLoader {
    private final Map<String, String> classPathMap = new HashMap<>();
    private final ClassLoader jdkClassLoader;


    public MyClassLoaderCustom(ClassLoader jdkClassLoader) {
        this.jdkClassLoader = jdkClassLoader;
    }

    public void addClassAndName(String className, String classPath) {
        classPathMap.put(className, classPath);
    }

//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String classPath = classPathMap.get(name);
//        File file = new File(classPath);
//        if (!file.exists()) {
//            throw new ClassNotFoundException();
//        }
//
//
//        byte[] classBytes = getClassData(file);
//
//        if (classBytes.length == 0) {
//            throw new ClassNotFoundException();
//        }
//
////        System.out.println(name);
//        return defineClass(name, classBytes, 0, classBytes.length);
//    }


    /**
     * 破坏双亲委派机制，java.lang中的类有jdk自己加载
     * 其他的类由用户自定义来加载
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result = null;
        try {
            result = jdkClassLoader.loadClass(name);
        } catch (Exception e) {
            //忽略
        }

        if (result != null) {
            return result;
        }

        String classPath = classPathMap.get(name);
        File file = new File(classPath);

        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] classBytes = getClassData(file);

        if (classBytes.length == 0) {
            throw new ClassNotFoundException();
        }

        return defineClass(name, classBytes, 0, classBytes.length);

    }

    private byte[] getClassData(File file) {
        try (InputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {


            byte[] buffer = new byte[4096];
            int byteNumRead = 0;
            while ((byteNumRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, byteNumRead);
            }

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[]{};
    }


}
