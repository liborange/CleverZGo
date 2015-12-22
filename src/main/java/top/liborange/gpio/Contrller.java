package top.liborange.gpio;

import top.liborange.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by liborange on 15/12/18.
 */
public class Contrller {

    public static void demo() throws InterruptedException {
        WheelUnity wheels = new WheelUnity(Config.Method.normal);

        for (int i = 3; i > 0; i--) {
            System.out.println(i+"秒后启动");
            Thread.sleep(1000);
        }

        wheels.run(Config.CMD.forward);
        Thread.sleep(2000);

        wheels.run(Config.CMD.back);
        Thread.sleep(2000);

        wheels.run(Config.CMD.right);
        Thread.sleep(4000);

        wheels.run(Config.CMD.left);
        Thread.sleep(4000);

        wheels.run(Config.CMD.stop);
    }

    public static void normal() throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        WheelUnity wheels = new WheelUnity(Config.Method.normal);
        boolean flag = true;
        System.out.println("RRRReady,Running man ！It's time to show ~~~ Baby Gogogo!");
        while(flag){
            char command = buffReader.readLine().toCharArray()[0];
            switch (command){
                case 'f':
                case 'w':
                    System.out.println("command: " + command + " 小车前进");
                    wheels.run(Config.CMD.forward);
                    break;
                case 'b':
                case 's':
                    System.out.println("command: " + command + " 小车后退");
                    wheels.run(Config.CMD.back);
                    break;
                case 'l':
                case 'a':
                    System.out.println("command: " + command + " 小车左转");
                    wheels.run(Config.CMD.left);
                    break;
                case 'r':
                case 'd':
                    System.out.println("command: " + command + " 小车右转");
                    wheels.run(Config.CMD.right);
                    break;
                case 'q':
                    wheels.run(Config.CMD.stop);
                    System.out.println("command: " + command + " 小车停止，等待命令");
                    break;
                case 'e':
                default:
                    wheels.run(Config.CMD.stop);
                    System.out.println("command: "+command+" 小车停止，退出程序");
                    flag = false;
                    break;
            }
        }
    }

    public static void pwm() throws IOException {
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        WheelUnity wheels = new WheelUnity(Config.Method.pwd);
        boolean flag = true;
         System.out.println("RRRReady,Running man ！It's time to show ~~~ Baby Gogogo!");
        while(flag){
            char command = buffReader.readLine().toCharArray()[0];
            switch (command){
                cxchong
                case 'w':
                    System.out.println("command: " + command + " 小车前进");
                    wheels.run(Config.CMD.forward);
                    break;
                case 'b':
                case 's':
                    System.out.println("command: "+command+" 小车后退");
                    wheels.run(Config.CMD.back);
                    break;
                case 'l':
                case 'a':
                    System.out.println("command: "+command+" 小车左转");
                    wheels.run(Config.CMD.left);
                    break;
                case 'r':
                case 'd':
                    System.out.println("command: "+command+" 小车右转");
                    wheels.run(Config.CMD.right);
                    break;
                case 'q':
                    wheels.run(Config.CMD.stop);
                    System.out.println("command: " + command + " 小车停止，等待命令");
                    break;
                case 'e':
                default:
                    wheels.run(Config.CMD.stop);
                    System.out.println("command: "+command+" 小车停止，退出程序");
                    flag = false;
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入运行模式，1：普通；2：pwm；3：demo");
        int i = scanner.nextInt();
        switch (i){
            case 1:
                normal();
                break;
            case 2:
                pwm();
                break;
            case 3:
                demo();
                break;
            default:
                break;
        }
    }
}
