package core.thread;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class WaitNotify {
    static boolean flag=true;
    static Object lock=new Object();  //任意对象，用来获取锁，然后执行通知/等待机制

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread=new Thread(new Wait(),"waitThread");
        waitThread.start();

        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread=new Thread(new Notify(),"notifyThread");
        notifyThread.start();

    }


    //等待线程
    static class Wait implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while(flag){
                    try {
                        System.out.println(Thread.currentThread().getName()+" flag is "+flag+" waiting "+ LocalTime.now());
                        lock.wait();    //lock.wait将会释放锁，然后该线程从运行态转化为就绪状态
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println( Thread.currentThread().getName()+" flag is "+flag+" running "+ LocalTime.now() );
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock){
                System.out.println( Thread.currentThread().getName()+" hold lock. notifying"+ LocalTime.now() );
                lock.notifyAll();   //唤醒随机1个wait线程
                flag=false;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock){
                System.out.println( Thread.currentThread().getName()+" again hold lock. "+ LocalTime.now() );
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
