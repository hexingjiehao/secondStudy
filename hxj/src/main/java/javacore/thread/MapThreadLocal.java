package javacore.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class MapThreadLocal {

    //此时绑定在main线程上,类似于getSet方法
    private static final ThreadLocal<Long> threadlocal=new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    //调用静态方法时自动初始化静态变量
    public static final void begin(){
        threadlocal.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis()-threadlocal.get();
    }

    //测试ThreadLocal类
    public static void main(String[] args) throws InterruptedException {
        MapThreadLocal.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("cost time:"+MapThreadLocal.end());
    }

}
