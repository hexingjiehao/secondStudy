package core.nio;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by xiongjie on 2018/11/17.
 */
public class TestReactor {

    @Test
    public void testConnect() throws Exception{
        Socket socket=new Socket("192.168.1.12",4700);//BIO 阻塞
        System.out.println("连接成功");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //下面这种写法，不用关闭客户端，服务器端也是可以收到的
        {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("hi");
            printWriter.flush();
        }

        byte[] buf = new byte[2048];
        System.out.println("准备读取数据~~");

        while(true){
            try {
                //两种读取数据方式
                int count = socket.getInputStream().read(buf);        //会阻塞
                System.out.println("方式一： 读取数据" + new String(buf) + " count = " + count);
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }

    }

    @Test
    public void testNioServer(){
        Thread server = new Thread(new Reactor());
        server.start();

        while(true){
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testBioServer(){
        Thread server = new Thread(new BioReactor());
        server.start();

        while(true){
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
