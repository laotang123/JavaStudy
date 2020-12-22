package com.golaxy.indi.chapter10;

/**
 * @author ：ljf
 * @date ：2020/11/15 15:48
 * @description：内部类实现多个事件的启动
 * @modified By：
 * @version: $ 1.0
 */
public class GreenHouseController extends Controller {
    private boolean light = false;

    public class LightOn extends Event {

        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = true;
        }
    }

    public class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            light = false;
        }
    }

    private boolean water = false;
    public class WaterOn extends Event{
        public WaterOn(long delayTime){
            super(delayTime);
        }

        @Override
        public void action() {
            water = true;
        }
    }

    public class WaterOff extends Event{
        public WaterOff(long delayTime){
            super(delayTime);
        }

        @Override
        public void action() {
            water = false;
        }


    }
}
