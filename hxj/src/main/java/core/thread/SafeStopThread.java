package core.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class SafeStopThread {

    /**
     * 使用interrupt和标记位判断安全终止线程。中断方法有时候并不能让线程终止，只是标记状态。安全停止，还是要线程内部执行完成才行。
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runner(),"countOneThread");
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

        Runner two=new Runner();
        thread=new Thread(two,"countTwoThread");
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on=true;

        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("name:"+Thread.currentThread().getName()+",count i="+i);
        }

        public void cancel(){
            on=false;
        }

    }

}
