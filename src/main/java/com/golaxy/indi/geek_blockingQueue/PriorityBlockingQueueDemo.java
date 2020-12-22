package com.golaxy.indi.geek_blockingQueue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author: ljf
 * @date: 2020/11/21 20:35
 * @description: 阻塞优先队列
 * @modified By:
 * @version: $ 1.0
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) {
        PriorityBlockingQueue<Integer> pbq = new PriorityBlockingQueue<>(3, Comparator.reverseOrder());

        pbq.add(1);
        pbq.add(2);
        pbq.add(3);

        for (int i = 0; i < 3; i++) {
            System.out.println(pbq.poll());
        }
    }
}
