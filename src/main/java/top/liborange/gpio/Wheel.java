package top.liborange.gpio;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import top.liborange.Config;

/**
 * Created by liborange on 15/12/18.
 */
public class Wheel {
    private GpioPinDigitalOutput forward;
    private GpioPinDigitalOutput backward;
    private int f,b;
    private boolean pwm = false;
    public Wheel(Pin F,Pin B) {
        forward = Config.gpio.provisionDigitalOutputPin(F, "", PinState.LOW);
        backward = Config.gpio.provisionDigitalOutputPin(B, "", PinState.LOW);
    }

    public Wheel(int f,int b){
        this.f = f;
        this.b = b;
        pwm = true;
        Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(f, 0, 100);
        SoftPwm.softPwmCreate(b, 0, 100);
        SoftPwm.softPwmWrite(f, 0);
        SoftPwm.softPwmWrite(b, 0);
    }

    /**
     * pwm模式下，通过参数调节车轮，参数【大小】控制转速，参数【正负】控制方向
     * @param power
     */
    public void move(int power){
        if(pwm) {
            power %= 100;
            if (power >= 0) {
                pwmSetting(power, 0);
            } else {
                pwmSetting(0, -power);
            }
        }
    }

    /**
     * 普通模式下，车轮的前进，后退，停止。
     * @return
     */
    public boolean forward(){
        forward.high();
        backward.low();
        return true;
    }

    public boolean back(){
        forward.low();
        backward.high();
        return true;
    }


    public boolean stop(){
        if(!pwm) {
            forward.low();
            backward.low();
        }else {
           pwmSetting(0,0);
        }
        return true;
    }

    /**
     * 参数大于零，输出高电压；小于零，输出低电压。
     * @param fpower
     * @param bpower
     */
    public void pwmSetting(int fpower,int bpower) {
        if (pwm) {
            System.out.println("power: "+fpower+" "+bpower);
            SoftPwm.softPwmWrite(b, bpower);
            SoftPwm.softPwmWrite(f, fpower);
        }
    }
}
