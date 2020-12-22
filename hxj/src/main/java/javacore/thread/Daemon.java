package javacore.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class Daemon {

    //jvm中没有daemon进程时或者程序执行完，jvm退出
    public static void main(String[] args){
        Thread thread=new Thread(new DaemonRunner(),"DaemonRunner");

        //设置当前进程为支持性进程
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{

        @Override
        public void run() {
            try{
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemoonThread finally run");
            }
        }
    }

}
