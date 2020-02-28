package javaKnowledge.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by xiongjie on 2018/10/28.
 * 测试使用自定义同步器来实现Lock，，对同步变量的同步读与写
 */
public class TwinLock implements Lock {

    private final XiongjieSync sync= new XiongjieSync(2);

    /**
     *静态内部类实现同步器，用于代理实现获取释放同步状态
     *同一时刻 ，只有两个线程能够获取锁
     */
    private static final class XiongjieSync extends AbstractQueuedSynchronizer{

        XiongjieSync(int count){
            if(count<0){
                throw  new IllegalArgumentException("同步器资源不能小于0");
            }
            setState(count);
        }

        //被重写方法，覆盖父类之间的被调用方法
        @Override
        protected int tryAcquireShared(int i) {
            for(;;){
                int current=getState();
                int newCount=current-i;
                if( newCount<0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }


        //共享式释放变量
        @Override
        protected boolean tryReleaseShared(int i) {
            for(;;){
                int current=getState();
                int newCount=current+i;
                if( compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock(){
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 在方法内声明类，只在方法内存活
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final Lock lock=new TwinLock();

        class Worker extends Thread{
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for(int i=0;i<10;i++){
            Worker w=new Worker();
            w.setDaemon(true);
            w.start();
        }

        for(int i=0;i<10000;i++){
            TimeUnit.SECONDS.sleep(1);
            System.out.println("*****华丽分割线*******");
        }

    }

}
