package top.liborange.gpio;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Created by liborange on 15/12/18.
 */
public class Wheel {
    public static final GpioController gpio  = GpioFactory.getInstance();
    private GpioPinDigitalOutput forward;
    private GpioPinDigitalOutput backward;
    private int f,b;
    private boolean pwm = false;
    public Wheel(Pin F,Pin B) {
        forward = gpio.provisionDigitalOutputPin(F, "", PinState.LOW);
        backward = gpio.provisionDigitalOutputPin(B, "", PinState.LOW);
    }

    public Wheel(int f,int b){
        this.f = f;
        this.b = b;
        pwm = true;
        SoftPwm.softPwmCreate(f, 0, 100);
        SoftPwm.softPwmCreate(b, 0, 100);
    }
    public void forward(){
        forward.high();
        backward.low();
    }
    public void move(int power){
        if(power>=0) {
            pwmSetting(power,0);
        }else {
            pwmSetting(0,-power);
        }
    }

    public void back(){
        forward.low();
        backward.high();
    }


    public void stop(){
        if(!pwm) {
            forward.low();
            backward.low();
        }else {
           pwmSetting(0,0);
        }
    }

    public void pwmSetting(int fpower,int bpower){
        fpower %= 100;
        bpower %= 100;
        SoftPwm.softPwmWrite(b, bpower);
        SoftPwm.softPwmWrite(f, fpower);
    }
}
