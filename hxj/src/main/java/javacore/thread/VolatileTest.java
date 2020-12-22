package javacore.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiongjie on 2018/10/26.
 */
public class VolatileTest {

    //********************volatile*****************************
    //原子操作，volatile写-读操作规则,cpu缓存刷新到内存
    volatile long v1=0L;

    //原子性
    public void set(long l){
        v1=l;
    }
    //原子性
    public long get(){
        return v1;
    }
    //没有原子性
    public void getAndIncreament(){
        v1++;
    }

    //********************synchronized*****************************
    long s=0L;
    //原子性
    public synchronized void setS(long s1){
        s=s1;
    }
    //原子性
    public synchronized long getS(){
        return s;
    }
    //没有原子性
    public void getAndIncreamentS(){
        long temp=getS();
        temp+=1L;
        setS(temp);
    }

    //********************ReentrantLock*****************************
    int a=0;
    ReentrantLock reentrantLock=new ReentrantLock();
    public void writer(){
        reentrantLock.lock(); //获取锁
        try {
            a++;
        }finally {
            reentrantLock.unlock();   //释放锁
        }
    }

    public void reader(){
        reentrantLock.lock();
        try {
            int i=a;
        }finally {
            reentrantLock.unlock();
        }
    }


}
