package top.liborange.gpio;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import top.liborange.Config;
import top.liborange.Config.Method;

/**
 * ----------Dragon be here!----------/
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑
 * 　　　　┃　　　┃代码无BUG！
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━━━━━━神兽出没━━━━━━━━━━━
 * <p>
 * Created by liborange on 15/12/22.
 */
public class CarUnity {

    WheelUnity wheels;
    public CarUnity(Method method) {
        wheels = new WheelUnity(method);
    }

    public void execute(Config.CMD cmd) {
        wheels.run(cmd);
    }

    public void execute(int a, int b, int c, int d) {
        wheels.turnAround(a, b, c, d);
    }



}
