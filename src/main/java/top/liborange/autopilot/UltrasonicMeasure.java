package top.liborange.autopilot;

import com.pi4j.io.gpio.*;
import top.liborange.Config;

/**
 * Created by liborange on 15/12/19.
 */
public class UltrasonicMeasure {
    private GpioPinDigitalOutput trig;
    private GpioPinDigitalInput echo;

    public UltrasonicMeasure(Pin trig,Pin echo) {
        this.trig = Config.gpio.provisionDigitalOutputPin(trig, "控制信号输出端口", PinState.LOW);
        this.echo = Config.gpio.provisionDigitalInputPin(echo, "信号输入端口");
    }

    private void trigging() {
        trig.high();
        //System.out.println(trig.getState());
        long pre = System.nanoTime();
        while (System.nanoTime() - pre < 1000)
            pre = System.nanoTime();
        trig.low();
    }

    /**
     * 返回距离障碍物的距离，单位为毫米
     * @return
     */
    public long measure() throws InterruptedException {
        while (true){
            long start,begin,end;
            boolean overtime = false;
            trigging();
            begin = start = System.nanoTime();

            while (echo.isLow()){
                if((System.nanoTime()-start)<2000000000)
                    begin = System.nanoTime();
                else {
                    overtime = true;
                    System.out.println("超时重测");
                    Thread.sleep(1000);
                }
            }

            if(!overtime){
                end = System.nanoTime();
                while(echo.isHigh())
                    end = System.nanoTime();
                long distance = (end-begin)/1000000000*17000;

                return  distance;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UltrasonicMeasure distance = new UltrasonicMeasure(RaspiPin.GPIO_04,RaspiPin.GPIO_05);
        while (true) {
            System.out.println("距离障碍物 " + distance.measure() + " cm");
            Thread.sleep(2000);
        }

    }
}
