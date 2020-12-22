package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/14 22:07
 * @description：模板方法，留出action方法进行扩展
 * @modified By：
 * @version: $ 1.0
 */
public abstract class Event {
    private long eventTime;
    private final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    /**
     * 设置事件的启动延时时间
     */
    public void start() {
        eventTime = System.nanoTime() + delayTime;
    }

    /**
     * 查看是否过了延迟时间
     * @return
     */
    public boolean ready(){
        return System.nanoTime() >= eventTime;
    }


    public abstract void action();
}
