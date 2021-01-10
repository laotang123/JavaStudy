package corejava.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/10 9:40
 * @description: jdk自身实现代理模式
 * 必须：含有接口
 * @modified By：
 * @version: $ 1.0
 */
class ObjectProxy implements InvocationHandler {

    /**
     * Object 自带的方法也会通过代理方式调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        System.out.println(method.getDeclaringClass());
//        System.out.println(Object.class == method.getDeclaringClass());
        String methodName = method.getName();
        if (Object.class == method.getDeclaringClass()) {
            switch (methodName) {
                case "equals":
                    return proxy == args[0];
                case "hashCode":
                    return System.identityHashCode(proxy);
                case "toString":
                    return proxy.getClass().getName() + "@" +
                            Integer.toHexString(System.identityHashCode(proxy)) + ", with InvocationHandler " + this;
                default:
                    throw new IllegalStateException(String.valueOf(method));
            }
        }


        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?>[] helloTypes1 = new Class<?>[]{String.class};
        Class<?>[] helloTypes2 = new Class<?>[]{Integer.class};
        Class<?>[] serverTypes1 = new Class<?>[]{String.class};
        Class<?>[] serverTypes2 = new Class<?>[]{Integer.class};


        switch (methodName) {
            case "hello":
                if (Arrays.equals(parameterTypes, helloTypes1)) {
                    System.out.println("函数调用前");
                    return "hello, " + args[0];
//                    System.out.println("函数调用后");
                } else if (Arrays.equals(parameterTypes, helloTypes2)) {
                    return "hello, " + (((Integer) (args[0])) + 100);
                }
            case "server":
                if (Arrays.equals(parameterTypes, serverTypes1)) {
                    return "hello, " + args[0];
                } else if (Arrays.equals(parameterTypes, serverTypes2)) {
                    return "hello, " + (((Integer) (args[0])) + 100);
                }
            default:
                throw new IllegalStateException(String.valueOf(method));
        }
    }
}

interface HelloService {
    String hello(String name);

    String hello(Integer i);

    String server(String name);

    String server(Integer i);
}

public class JDKProxy {
    public static void main(String[] args) {
        ClassLoader classLoader = HelloService.class.getClassLoader();
        Class<?>[] interfaces = new Class<?>[]{HelloService.class};
        ObjectProxy objectProxy = new ObjectProxy();
        HelloService helloService = (HelloService) Proxy.newProxyInstance(classLoader, interfaces, objectProxy);

        String result = helloService.hello("tom");
        System.out.println("hello,String: " + result);
        String result1 = helloService.hello(1);
        System.out.println("hello,Integer: " + result1);

        String result2 = helloService.server("getName");
        System.out.println("hello,String: " + result2);
        String result3 = helloService.hello(2);
        System.out.println("hello,Integer: " + result3);
    }

}
