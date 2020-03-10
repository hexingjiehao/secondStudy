package core.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/27.
 */
public class ThreadPriority {

    private static volatile  boolean notStart=true;
    private static volatile  boolean notEnd=true;


    //程序正确性不依靠线程优先级,概率事件，windows能努力实现优先级的设定
    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs=new ArrayList<Job>();
        List<Thread> list=new ArrayList<Thread>();
        for (int i=1;i<=10;i++){
            int priority=i<=5?Thread.MIN_PRIORITY:Thread.MAX_PRIORITY;
            Job job=new Job(priority);
            jobs.add(job);

            Thread thread=new Thread(job,"Thread:"+i);
            thread.setPriority(priority);
            list.add(thread);
            thread.start();
        }

        notStart=false;
        TimeUnit.SECONDS.sleep(1);  //内部使用线程休眠
        notEnd=false;

//        确保线程执行完毕
        for (Thread thread:list){
            thread.join();
        }

        //打印线程执行后的结果
        for (Job job:jobs){
               System.out.println("Job priority:"+job.priority+",count:"+job.jobCount);
        }
    }

    static class Job implements Runnable{

        private int priority;
        private long jobCount;

        public Job(int priority){
            this.priority=priority;
        }

        @Override
        public void run() {
            while (notStart){
                Thread.yield();
            }
            while (notEnd){

                /**
                 * yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
                 * 证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
                 */
                Thread.yield();
                jobCount++;
            }

        }
    }

}
