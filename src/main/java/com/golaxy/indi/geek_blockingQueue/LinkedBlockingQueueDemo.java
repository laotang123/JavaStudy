package com.golaxy.indi.geek_blockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: ljf
 * @date: 2020/11/21 20:19
 * @description: 阻塞链表
 * @modified By:
 * @version: $ 1.0
 */
public class LinkedBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>(3);
        linkedBlockingQueue.put(1);
        linkedBlockingQueue.put(2);
        linkedBlockingQueue.put(3);
        linkedBlockingQueue.add(4);
//        linkedBlockingQueue.put(4);

        System.out.println(linkedBlockingQueue);
    }
}
