package javaKnowledge.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xiongjie on 2018/10/29.
 * 测试阻塞器类CountDownLatch的使用方法
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch=new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                //释放其中1个共享锁
                countDownLatch.countDown();
                countDownLatch.countDown();
                System.out.println(2);
                countDownLatch.countDown();
            }
        }).start();

        //这里的意思是只有new的线程中的操作完成之后，主线程才开始执行
        countDownLatch.await();
        System.out.println(3);

    }

}
