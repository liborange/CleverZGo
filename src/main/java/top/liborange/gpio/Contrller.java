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
        while(flag){
            char command = buffReader.readLine().toCharArray()[0];
            switch (command){
                case 'f':
                case 'w':
                    wheels.forward();
                    break;
                case 'b':
                case 's':
                    wheels.back();
                    break;
                case 'l':
                case 'a':
                    wheels.left();
                    break;
                case 'r':
                case 'd':
                    wheels.right();
                    break;
                case 'e':
                    flag = false;
                    break;
                default:
                    flag = false;
                    break;
            }
        }
    }
}
