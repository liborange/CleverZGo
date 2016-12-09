package top.liborange;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * Created by liborange on 15/12/19.
 */
public class Config {
    public static final GpioController gpio  = GpioFactory.getInstance();
    public enum CMD{
        forward, back, left, right, fl, fr, stop, round,   //小车运动方向控制类
        powerUp, powerDown, levelUp, levelDown,      //参数调节类

    }
    public enum Method{
        pwd,
        normal
    }
}