package top.liborange.connection;

import top.liborange.Config;
import top.liborange.gpio.CarUnity;

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
public class CmdServiceImpl implements CmdService {

    private CarUnity car;

    public CmdServiceImpl(CarUnity car){
        this.car = car;
    }

    public String executeCMD(Object cmd) {
        car.execute((Config.CMD)cmd);
        return "ok";
    }

    public String executeCMD(int a, int b, int c, int d) {
        car.execute(a, b, c, d);
        return "ok";
    }
}
