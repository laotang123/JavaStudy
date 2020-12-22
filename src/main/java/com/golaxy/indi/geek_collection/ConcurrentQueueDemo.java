package com.golaxy.indi.geek_collection;

import java.util.AbstractQueue;
import java.util.PriorityQueue;

/**
 * @author: ljf
 * @date: 2020/11/19 22:47
 * @description: 并发队列的测试
 * concurrent并发加锁的原理
 * @modified By:
 * @version: $ 1.0
 */
public class ConcurrentQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(10);
        priorityQueue.add(30);
        priorityQueue.add(20);

//        for (Integer item : priorityQueue) {
//            System.out.println(item);
//        }

        for (int i = 0; i < 3; i++) {
            System.out.println(priorityQueue.poll());
        }
    }
}
