package javaKnowledge.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class MultilThread {

    /**
     * 简单main方法的多线程实现
     * @param args
     */
    public static void main(String[] args){
        //获取java线程管理mxbean
        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfo=threadMXBean.dumpAllThreads(false,false);
        for (ThreadInfo th:threadInfo){
            System.out.println("id="+th.getThreadId()+",name="+th.getThreadName());
        }

    }

}
