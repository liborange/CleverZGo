package top.liborange.gpio;

import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import top.liborange.Config;

/**
 * Created by liborange on 15/12/18.
 */
public class WheelUnity {
    private Wheel FL;   //前左车轮
    private Wheel FR;   //前右车轮
    private Wheel BL;   //后左车轮
    private Wheel BR;   //后右车轮

    private boolean pwm = false;
    private int power = 50; //power范围0~100，值越大供应电流越强，车轮力量越强
    private int level = 0;  //level共有10级，级数越大，车轮协同转弯力越强，转弯越快

    public WheelUnity(Config.Method method){
        if(method == Config.Method.normal){
            FL = new Wheel(RaspiPin.GPIO_00, RaspiPin.GPIO_01);
            FR = new Wheel(RaspiPin.GPIO_02, RaspiPin.GPIO_03);
            BL = new Wheel(RaspiPin.GPIO_24, RaspiPin.GPIO_27);
            BR = new Wheel(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
        }else if(method == Config.Method.pwd){
            pwm = true;
            FL = new Wheel(0, 1);
            FR = new Wheel(2, 3);
            BL = new Wheel(24, 27);
            BR = new Wheel(28, 29);
        }
    }

    public boolean run(Config.CMD cmd){
        switch (cmd){
            case forward:
                turnAround(power,power,power,power);
                break;
            case back:
                turnAround(-power,-power,-power,-power);
                break;
            case left:
                turnAround(power, power, -level * power / 10, -(level + 2) % 10 * power / 10);
                break;
            case right:
                turnAround(-level * power / 10, -(level + 2) % 10 * power / 10, power, power);
                break;
            case fl:
                turnAround(power, power, -(level-2) * power / 10, -(level)%10* power/ 10);
                break;
            case fr:
                turnAround(-(level-2) * power / 10, -(level) % 10 * power / 10, power, power);
                break;
            case stop:
                turnAround(0,0,0,0);
                break;
            case round:     //小车大回环
                turnAround(power,power,-power,-power);
                break;
            case powerUp:
                setPower(+10);
                break;
            case powerDown:
                setPower(-10);
                break;
            case levelUp:
                setLevel(+1);
                break;
            case levelDown:
                setLevel(-1);
                break;
        }
        return true;
    }

    private void setLevel(int plus){
        level += plus;
        level = level<0?0:level%10;

    }

    private void setPower(int plus) {
        power += plus;
        power = power<10?10:power%100;  //保证power大于10，小于100
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
     * 参数依次为前右，后右，前左，后左的车轮系数
     * pwm模式下，四个参数的 【大小】代表车轮的转速，【正零负】代表前转、不转、后转
     * noraml模式下，四个参数的【大小】没有意义，【正零负】代表前转、不转、后转
     * @param fr
     * @param br
     * @param fl
     * @param bl
     */
    public void turnAround(int fr,int br,int fl, int bl){
        if(pwm) {
            FL.move(fl);
            FR.move(fr);
            BR.move(br);
            BL.move(bl);
        }else {
            boolean bool = fr>0?FR.forward():(fr == 0?FR.stop():FR.back());
            bool = br>0?BR.forward():br==0?BR.stop():BR.back();
            bool = fl>0?FL.forward():fl==0?FL.stop():FL.back();
            bool = bl> 0 ?BL.forward():bl==0?BL.stop():BL.back();
        }
    }
}
