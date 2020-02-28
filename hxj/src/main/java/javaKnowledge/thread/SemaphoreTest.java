package javaKnowledge.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by xiongjie on 2018/10/29.
 * 测试信号量的并发使用
 */
public class SemaphoreTest {

    private static ExecutorService executorService= Executors.newFixedThreadPool(30);
    private  static Semaphore s=new Semaphore(10); //1次只允许10个并发执行

    public static void main(String[] args){
        for(int i=0;i<30;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(Thread.currentThread().getName());
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //关掉线程
        executorService.shutdown();
    }

}
