package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/14 22:11
 * @description：消息事件
 * @modified By：
 * @version: $ 1.0
 */
public class MessageEvent extends Event {

    public MessageEvent(long eventTime){
        super(eventTime);
    }
    @Override
    public void action() {
        System.out.println("message event action...");
    }
}
