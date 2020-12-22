package com.golaxy.indi.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ljf
 * @date ：2020/11/15 15:51
 * @description：控制器类
 * @modified By：
 * @version: $ 1.0
 */
public class Controller {
    private List<Event> eventList = new ArrayList<>();

    public int getEventSize(){
        return eventList.size();
    }
    public void addEvent(Event e) {
        eventList.add(e);
    }


    /**
     * 启动controller中的事件
     */
    public void run() {
        while (eventList.size() > 0){
            for (Event event : new ArrayList<>(eventList)) {
                if (event.ready()) {
                    System.out.println(event);
                    event.action();
                    eventList.remove(event);
                }
            }
        }
    }
}
