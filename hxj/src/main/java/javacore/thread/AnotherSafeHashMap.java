package javacore.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by xiongjie on 2018/10/28.
 * 使用读写锁保证hashmap的线程安全
 */
public class AnotherSafeHashMap {

    static Map<String,Object> map=new HashMap<>();
    static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    static Lock readLock=reentrantReadWriteLock.readLock();
    static Lock writeLock=reentrantReadWriteLock.writeLock();

    //final表示该方法不能被重写
    public static final Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            //资源，锁释放
            readLock.unlock();
        }
    }

    public static final Object put(String key,Object value){
        writeLock.lock();
        try {
            return map.put(key,value);
        }finally {
            //资源，锁释放
            writeLock.unlock();
        }
    }

    public static final void clear(){
        writeLock.lock();
        try {
            map.clear();
        }finally {
            //资源，锁释放
            writeLock.unlock();
        }
    }

}



