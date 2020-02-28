package javaKnowledge.thread;

import java.util.concurrent.*;

/**
 * Created by xiongjie on 2018/10/29.
 */
public class ExchangerTest {

    //按顺序两两交换，然后撤退，奇数的交换会卡死,最好设置交换过期时间，超时后会抛异常
    private static final Exchanger<String> e=new Exchanger<>();
    private static ExecutorService service= Executors.newFixedThreadPool(3);

    public static void main(String[] args){

        service.execute(new Runnable() {
            @Override
            public void run() {
                String A="数据A";
                try {
                    String resA=e.exchange(A);
                    System.out.println("A线程交换后的值："+resA);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                String C="数据C";
                try {
                    String resC=e.exchange(C);
                    System.out.println("C线程交换后的值："+resC);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                String B="数据B";
                try {
                    String resB= null;
                    try {
                        resB = e.exchange(B,1, TimeUnit.SECONDS);
                    } catch (TimeoutException e1) {
                        resB="没有交换的数据";
                    }
                    System.out.println("B线程交换后的值："+resB);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }


}
