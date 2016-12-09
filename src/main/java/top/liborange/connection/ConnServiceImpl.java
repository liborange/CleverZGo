package top.liborange.connection;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Created by liborange on 16/1/10.
 */
public class ConnServiceImpl implements ConnService {
    public String sendInfo(String info) {
        System.out.println(info);
        writeMethod(info);
        return null;
    }
    public static void writeMethod(String info) {
        String fileName="/wechatlog.txt";
        try {
            FileWriter writer=new FileWriter(fileName,true);
            SimpleDateFormat format=new SimpleDateFormat();
            String time=format.format(new Date());
            writer.write("\n" + time+":\t"+info);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
