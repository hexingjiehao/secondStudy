package javaKnowledge.thread;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiongjie on 2018/10/25.
 */
public class ConCurrencyTest {

    private static final long count=10L;

    public static void main(String[] args) throws InterruptedException {
//        concurrency();
        includeThread();
    }

    //thread.join()实现线程之间的嵌套
    private static void includeThread() throws InterruptedException {
        Thread previous=Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread thread=new Thread(new Domino(previous,String.valueOf(i)) );
            thread.start();
            previous=thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName()+": treminate");
    }

    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread, String s) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": treminate");
        }
    }

    /**
     * 测试thread.join()的作用
     * @throws InterruptedException
     */
    private static void concurrency() throws InterruptedException {
        long start=System.currentTimeMillis();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for(long i=0;i<count;i++){
                    System.out.println("new thread is running,value="+i);
                }
            }
        });
        thread.start();
        for (long j=0;j<count;j++) {
            System.out.println("main thread is running,value="+j);
        }
        //thread.join的作用更像是插队。
        thread.join();
        long time=System.currentTimeMillis()-start;
        System.out.println("concurrency take time is "+time+" ms");
    }

}
