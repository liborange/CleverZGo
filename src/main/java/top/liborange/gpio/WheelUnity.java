package top.liborange.gpio;

import com.pi4j.io.gpio.RaspiPin;

/**
 * Created by liborange on 15/12/18.
 */
public class WheelUnity {
    private Wheel left = new Wheel(RaspiPin.GPIO_00, RaspiPin.GPIO_01);
    private Wheel right = new Wheel(RaspiPin.GPIO_02, RaspiPin.GPIO_03);

    public void forward(){
        left.forward();
        right.forward();
    }

    public void right(){
        left.forward();
        right.back();
    }

    public void left(){
        left.back();
        right.forward();
    }
    public void back(){
        left.back();
        right.back();
    }
    public void stop(){
        left.stop();
        right.stop();
    }
}
