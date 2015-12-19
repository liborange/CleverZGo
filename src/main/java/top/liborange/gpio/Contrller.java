package top.liborange.gpio;

import com.pi4j.io.gpio.RaspiPin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liborange on 15/12/18.
 */
public class Contrller {

    public static void normal() throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        WheelUnity wheels = new WheelUnity(RaspiPin.GPIO_00,RaspiPin.GPIO_01,RaspiPin.GPIO_02,RaspiPin.GPIO_03);
        boolean flag = true;
        System.out.println("RRRReady,Running man ！It's time to show ~~~ Baby Gogogo!");
        while(flag){
            char command = buffReader.readLine().toCharArray()[0];
            switch (command){
                case 'f':
                case 'w':
                    System.out.println("command: "+command+" 小车前进");
                    wheels.forward();
                    break;
                case 'b':
                case 's':
                    System.out.println("command: "+command+" 小车后退");
                    wheels.back();
                    break;
                case 'l':
                case 'a':
                    System.out.println("command: "+command+" 小车左转");
                    wheels.left();
                    break;
                case 'r':
                case 'd':
                    System.out.println("command: "+command+" 小车右转");
                    wheels.right();
                    break;
                case 'q':
                    wheels.stop();
                    System.out.println("command: " + command + " 小车停止，等待命令");
                    break;
                case 'e':
                default:
                    wheels.stop();
                    System.out.println("command: "+command+" 小车停止，退出程序");
                    flag = false;
                    break;
            }
        }
    }

    public static void pwm() throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        WheelUnity wheels = new WheelUnity(0,1,2,3);
        boolean flag = true;
        int basePower = 50;
        float level = 0.0f;
        System.out.println("PWM model,basePower = "+ basePower);
        System.out.println("RRRReady,Running man ！It's time to show ~~~ Baby Gogogo!");
        while(flag){
            char command = buffReader.readLine().toCharArray()[0];
            switch (command) {
                case 'u':
                    level -= 0.2;
                    level %= 0.8;
                    System.out.println("强度增强,当前左右扭矩差："+ (basePower-(int)(level * basePower)));
                    break;
                case 'y':
                    level += 0.2;
                    level %= 0.8;
                    System.out.println("强度减弱,当前左右扭矩差："+ (basePower-(int)(level * basePower)));
                    break;
                case 'i':
                    basePower += 10;
                    System.out.println("basePower加10，当前为："+basePower);
                    break;
                case 'o':
                    basePower -= 10;
                    System.out.println("basePower减10，当前为："+basePower);
                    break;
                case 'w':
                    System.out.println("command: " + command + " 小车前进");
                    wheels.forward(basePower);
                    break;
                case 's':
                    System.out.println("command: " + command + " 小车后退");
                    wheels.back(basePower);
                    break;
                case 'a':
                    System.out.println("command: " + command + " 小车左转");
                    wheels.turnAround((int)(level * basePower), basePower);
                    break;
                case 'd':
                    System.out.println("command: " + command + " 小车右转");
                    wheels.turnAround(basePower, (int)(level * basePower));
                    break;
                case 'q':
                    wheels.stop();
                    System.out.println("command: " + command + " 小车停止，等待命令");
                    break;
                case 'e':
                default:
                    wheels.stop();
                    System.out.println("command: " + command + " 小车停止，退出程序");
                    flag = false;
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Contrller.pwm();
    }
}
