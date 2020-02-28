package javaKnowledge.thread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class Interrupted {

    //测试线程的中断标志位的使用特点
    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread=new Thread(new SleepRunner(),"sleepRunner");
        sleepThread.setDaemon(true);
        //以支持性线程在后台执行
        Thread busyThread=new Thread(new BusyRunner(),"busyRunner");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        //保证后台进程充分执行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread interrupted is "+sleepThread.isInterrupted());
        System.out.println("busyThread interrupted is "+busyThread.isInterrupted());
        //防止线程立刻退出
        TimeUnit.SECONDS.sleep(2);

    }

    //睡眠线程
    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //运行线程
    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while(true){

            }
        }
    }
}
