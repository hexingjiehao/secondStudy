package hardware;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: hardware
 * @className ConnectURL
 * @description: JAVA程序判断电脑是否连接到Internet网络
 * @author: xj
 * @create: 2021-02-09 15:52:53
 **/
public class ConnectURL {

    public static void main(String[] args) {
        try {
            URL url = new URL( "http://baidu.com/");
            InputStream in  =  url.openStream();
            System.out.println( "Internet正常 ");
            in.close();
        } catch (IOException e) {
            System.out.println( "无法连接到： Internet");
        }
    }

}
