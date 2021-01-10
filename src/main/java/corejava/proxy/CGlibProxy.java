package corejava.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: ljf
 * @date: 2021/1/10 11:53
 * @description: cglib实现动态代理
 * @modified By：
 * @version: $ 1.0
 */

class TargetClass{
    public void targetInfo(){
        System.out.println("真实目标类被调用");
    }
}

class MyInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("插入前置通知");
        methodProxy.invokeSuper(o,objects);
        System.out.println("插入后置通知");
        return null;
    }
}
public class CGlibProxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //设置目标类
        enhancer.setSuperclass(TargetClass.class);
        //设置拦截对象
        enhancer.setCallback(new MyInterceptor());
        //创建代理子类
        TargetClass targetClass = (TargetClass) enhancer.create();
        targetClass.targetInfo();


    }
}
