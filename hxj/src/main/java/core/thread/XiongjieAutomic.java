package core.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiongjie on 2018/10/26.
 */
public class XiongjieAutomic {

    private AtomicInteger atomicInteger=new AtomicInteger(0);
    private int num=0;


    /**
     * 测试安全原子操作CAS算法的实现
     * @param args
     */
    public static void main(String[] args){
        final XiongjieAutomic  xiongjieAutomic=new XiongjieAutomic();
        List<Thread> list=new ArrayList<Thread>(600);
        long start=System.currentTimeMillis(); //统计时间

        //多线程处理
        for (int j=0;j<100;j++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int k=0;k<10000;k++){
                        xiongjieAutomic.unsafeCount();
                        xiongjieAutomic.safeCount();
                    }
                }
            });
            list.add(t);
        }

        for(Thread t:list){
            t.start();
        }

        //等待所有线程执行完毕,每次只中断main线程
        for(Thread t:list){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(xiongjieAutomic.num);
        System.out.println(xiongjieAutomic.atomicInteger.get());
        System.out.println(System.currentTimeMillis()-start);

    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount(){
        for(;;){
            int i=atomicInteger.get();
            boolean suc=atomicInteger.compareAndSet(i,++i);
            if(suc){
                break;
            }else{
                System.out.println("失败");
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void unsafeCount(){
       num++;
    }

}
