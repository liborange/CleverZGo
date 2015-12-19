package top.liborange.gpio;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

/**
 * Created by liborange on 15/12/18.
 */
public class WheelUnity {
    private Wheel left;
    private Wheel right;
    private boolean pwm = false;
    public WheelUnity(Pin LF,Pin LB,Pin RF,Pin RB){
        left = new Wheel(LF,LB);
        right = new Wheel(RF,RB);
    }
    public WheelUnity(int lf,int lb,int rf,int rb) {
        pwm = true;
        left = new Wheel(lf, lb);
        right = new Wheel(rf, rb);
    }


    public void forward(){
        left.forward();
        right.forward();
    }

    public void forward(int power){
        left.move(power);
        right.move(power);
    }


    public void back(){
        left.back();
        right.back();
    }
    public void back(int power){
        left.move(-power);
        right.move(-power);
    }

    public void left(){
        left.stop();
        right.forward();
    }

    public void right(){
        left.forward();
        right.stop();
    }

    /**
     * LPower和RPower的正负决定左轮和右轮的正转和反转。
     * .----------------------------------.
     * | LPower | RPower | 方向 | 描述     |
     * .----------------------------------.
     * | >0  <R | >0  >L | 左转 | 柔和左转  |
     * .----------------------------------.
     * |   < 0  |   > 0  | 左转 | 加速左转  |
     * .----------------------------------.
     * | >0  >R | >0  <L | 右转 | 柔和右转  |
     * .----------------------------------.
     * |   > 0  |   < 0  | 右转 | 强烈右转  |
     * .----------------------------------.
     *
     *
     * @param LPower
     * @param RPower
     */
    public void turnAround(int LPower,int RPower){
        LPower %= 100;
        RPower %= 100;
        left.move(LPower);
        right.move(RPower);
    }


    public void stop(){
        left.stop();
        right.stop();
    }

}
