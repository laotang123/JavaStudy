package com.golaxy.indi.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ljf
 * @date ：2020/11/14 22:13
 * @description：测试事件启动
 * @modified By：
 * @version: $ 1.0
 */

public class EventDemo {
    public static void main(String[] args) {
//        Controller controller = new Controller();
//        controller.addEvent(new MessageEvent(1000000000));
//        controller.addEvent(new MessageEvent(1000));
//        controller.addEvent(new MessageEvent(1000));
//        controller.run();
//        System.out.println(controller.getEventSize());
        GreenHouseController gc = new GreenHouseController();
        gc.addEvent(gc.new WaterOn(0));
        gc.addEvent(gc.new LightOn(2000));
        gc.run();
    }
}
