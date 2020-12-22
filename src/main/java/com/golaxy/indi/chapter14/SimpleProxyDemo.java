package com.golaxy.indi.chapter14;

import net.mindview.util.Print;

/*
 * @Author: ljf
 * @Date: 2020-11-26 22:48:44
 * @LastEditTime: 2020-11-26 23:07:51
 * @Description: 动态代理，将业务之外的逻辑封装到代理类中。比如程序的调用次数。调用开销
 * 在每一次执行具体的业务的前后封装代理逻辑
 */

interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        Print.print("real object do something");

    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("something else" + arg);

    }

}

// 代理类的实际业务类公用一个接口，更能清楚业务类的逻辑
// 在调用实际类处理业务之前，处理业务之外的逻辑
class SimpleProxy implements Interface {
    private Interface proxied;

    @Override
    public void doSomething() {
        System.out.println("proxy class processed");
        proxied.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("proxy class method somethingElse");
        proxied.somethingElse(arg);
    }

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

}

public class SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("tom");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
