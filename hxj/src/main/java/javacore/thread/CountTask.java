package core.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xiongjie on 2018/10/29.
 */
public class CountTask extends RecursiveTask<Integer> {

    //静态变量且不能被修改
    private static final int  THRESHOLD=2;
    private int start,end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        //2分法实现计算
        int sum=0;
        boolean canCompute= (end-start) <=THRESHOLD;
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum+=i;
            }
        }else{
            //开始2分
            int middle=(start+end)/2;
            CountTask leftTask=new CountTask(start,middle);
            CountTask rightTask=new CountTask(middle+1,end);

            //递归形式
            leftTask.fork();
            rightTask.fork();
            int leftResult=leftTask.join();
            int rightResult=rightTask.join();
            sum=leftResult+rightResult;

        }
        return sum;
    }

    //单线程最后可拆分为多线程并行执行任务
    public static void main(String[] args){

        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask countTask=new CountTask(1,1000000);
        long start=System.currentTimeMillis();
        Future<Integer> result=forkJoinPool.submit(countTask);
        long end=System.currentTimeMillis();
        try {
            System.out.println("fork/join累加结果:"+result.get()+",花费时间"+(end-start));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        long begin=System.currentTimeMillis();
        int res=0;
        for(int i=1;i<=1000000;i++){
            res+=i;
        }
        long stop=System.currentTimeMillis();
        System.out.println("for循环累加结果:"+res+",花费时间"+(stop-begin));
    }

}
