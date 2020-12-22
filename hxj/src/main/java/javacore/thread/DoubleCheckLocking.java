package javacore.thread;

import java.util.Date;

/**
 * Created by xiongjie on 2018/10/26.
 */
public class DoubleCheckLocking {

    private static Date timer;
    private static volatile Date safetimer;

    private static class TimeHolder{
        public static Date classtime=new Date();
    }

    /*
     * 不安全的双重检查锁定
     */
    public static Date getInstance(){
        if(timer==null){
            synchronized (DoubleCheckLocking.class){
                if(timer==null){
                    timer=new Date();

                    /**上面的new操作可拆分为
                     *1.分配对象的内存空间
                     *2.初始化对象
                     * 3.设置instance指向刚分配的内存地址
                     *
                     *注意：2和3可能重排序。多线程时出错
                     */
                }
            }
        }
        return timer;
    }

    /*
     * 安全的双重检查锁定
     * 不允许重排初始化和分配内存指向
     */
    public static Date safeGetInstance(){
        if(safetimer==null){
            synchronized (DoubleCheckLocking.class){
                if(safetimer==null){
                    safetimer=new Date();
                }
            }
        }
        return safetimer;
    }

    /*
     * 安全的初始化静态类
     * 通过封装在静态内部类中来安全初始化
     * 即使重排了，结果也正确
     */
    public static Date safeGetInnerInstance(){
        return TimeHolder.classtime;    //此时会将静态内部类的静态变量会初始化
    }


}
