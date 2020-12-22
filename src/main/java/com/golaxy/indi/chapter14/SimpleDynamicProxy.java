/*
 * @Author: ljf
 * @Date: 2020-11-26 23:09:46
 * @LastEditTime: 2020-11-26 23:35:07
 * @Description: 基于接口反射实现的动态代理，能够针对无参和有参方法同一调用。不用重复写很多类似代码
 */
package com.golaxy.indi.chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理类的handler
 */
class DynamicProxyHandler implements InvocationHandler {
    private Interface proxied;

    public DynamicProxyHandler(Interface proxied){
        this.proxied = proxied;
    }
    /**
     * 传入被代理对象，方法和参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("*** proxy: " + proxy.getClass() + ", method:" + method + ", args" + args);
        if (args != null)
            for (Object arg : args) {
                System.out.println(arg);
            }

//        try {
            return method.invoke(proxied, args);
//        } catch (InvocationTargetException e) {
//            throw e.getCause();
//        }
    }

}

public class SimpleDynamicProxy {

    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("lili");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);

        // 使用java的动态代理进行包装
        Interface proxy = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(),
                new Class[] { Interface.class }, new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
