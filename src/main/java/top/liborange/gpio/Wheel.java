package top.liborange.gpio;

import com.pi4j.io.gpio.*;

/**
 * Created by liborange on 15/12/18.
 */
public class Wheel {
    public static final GpioController gpio  = GpioFactory.getInstance();
    private final GpioPinDigitalOutput forward;
    private final GpioPinDigitalOutput backward;

    public Wheel(Pin F,Pin B) {
        forward = gpio.provisionDigitalOutputPin(F, "", PinState.LOW);
        backward = gpio.provisionDigitalOutputPin(B, "", PinState.LOW);
    }

    public void forward(){
        forward.high();
        backward.low();
    }

    public void back(){
        forward.low();
        backward.high();
    }

    public void stop(){
        forward.low();
        backward.low();
    }
}
