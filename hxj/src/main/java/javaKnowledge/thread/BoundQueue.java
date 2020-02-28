package javaKnowledge.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiongjie on 2018/10/28.
 * 使用1个lock+2个condition实现有界队列的多线程同步锁
 * 泛型工具类
 */
public class BoundQueue<T> {

    private Object[] items;
    private int addIndex,removeIndex,count; //默认值0

    //可重入锁
    private Lock lock=new ReentrantLock();
    private Condition notEmpty=lock.newCondition();
    private Condition notFull=lock.newCondition();

    public BoundQueue(int size) {
        items=new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while(count==items.length){
                notFull.await();
            }
            items[addIndex]=t;
            if(++addIndex==items.length){
                addIndex=0;
            }
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count==0){
                notEmpty.await();
            }
            Object x=items[removeIndex];
            if(++removeIndex==items.length){
                removeIndex=0;
            }
            --count;
            notFull.signal();
            return (T) x;
        }finally {

        }
    }

}
