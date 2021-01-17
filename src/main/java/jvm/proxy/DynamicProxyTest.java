package jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ljf
 * @date: 2021/1/16 22:17
 * @description: java字节码生成之动态代理
 * @modified By:
 * @version: $ 1.0
 */
interface IHello {
    void sayHello();
}

class Hello implements IHello {
    @Override
    public void sayHello() {
        System.out.println("hello, world");
    }
}


class DynamicProxy implements InvocationHandler {

    Object originalObject;


    Object bind(Object originalObject) {
        this.originalObject = originalObject;
        ClassLoader classLoader = originalObject.getClass().getClassLoader();
        Class<?>[] interfaces = originalObject.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("welcome");
        return method.invoke(originalObject, args);
    }


}

public class DynamicProxyTest {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
