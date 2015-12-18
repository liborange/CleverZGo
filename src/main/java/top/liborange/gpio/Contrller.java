package top.liborange.gpio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by liborange on 15/12/18.
 */
public class Contrller {
    public static void main(String[] args) throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        WheelUnity wheels = new WheelUnity();
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
}
