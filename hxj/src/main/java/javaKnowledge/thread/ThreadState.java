package javaKnowledge.thread;

import com.sun.org.apache.xerces.internal.parsers.CachingParserPool;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 * 使用jstack查看线程状态
 * 具体步骤：
 */
public class ThreadState {

    public static void main(String[] args){
        new Thread(new TimeWaiting(),"timewaitingthread").start();
        new Thread(new Waiting(),"waitingthread").start();
        new Thread(new Blocked(),"blockedthread-1").start();
        new Thread(new Blocked(),"blockedthread-2").start();
    }

    //模拟超时等待状态
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //模拟等待状态
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while(true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //模拟阻塞状态
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
