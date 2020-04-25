package core.thread;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 * 测试过期的线程暂停，恢复，停止方法
 */
public class Deprecated {

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runner(),"printThread");
        thread.setDaemon(true);
        thread.start();

        //睡眠的时间不是精确的
        TimeUnit.SECONDS.sleep(1);
        //暂停
        thread.suspend();
        System.out.println("main 暂停 printThread at:"+LocalTime.now());
        TimeUnit.SECONDS.sleep(3);
        //恢复
        thread.resume();
        System.out.println("main 恢复 printThread at:"+LocalTime.now());
        TimeUnit.SECONDS.sleep(3);
        //停止
        thread.stop();
        System.out.println("main 停止 printThread at:"+LocalTime.now());

    }

    static class  Runner implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println(Thread.currentThread().getName()+" run at:"+ LocalTime.now());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
