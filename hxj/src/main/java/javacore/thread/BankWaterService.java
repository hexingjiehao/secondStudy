package core.thread;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by xiongjie on 2018/10/29.
 * 测试CyclicBarrier类的使用
 */
public class BankWaterService implements Runnable {

    //第2个参数是线程类
    private CyclicBarrier c=new CyclicBarrier(4,this);
    private Executor executor= Executors.newFixedThreadPool(4);  //线程执行器

    //安全的多线程共享变量
    private ConcurrentHashMap<String,Integer> map=new ConcurrentHashMap<>();


    @Override
    public void run() {
         int result=0;
         for(Map.Entry<String,Integer> entry:map.entrySet()){
             result+=entry.getValue();
         }
         map.put("result",result);
        System.out.println(result);
    }


    public static void main(String[] args){
        BankWaterService bankWaterService=new BankWaterService();
        bankWaterService.count();

    }

    private void count() {
        for(int i=0;i<4;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    map.put(Thread.currentThread().getName(),1);
                    try {
                        System.out.println(Thread.currentThread().getName());
                        c.await();
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
