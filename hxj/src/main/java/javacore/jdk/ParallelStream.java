package javacore.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/19.
 */
public class ParallelStream {

    //测试并行Stream
    public static void main(String[] args){

        List<String> list =new ArrayList<>();
        list.add("ddd2");list.add("aaa2");list.add("bbb1");
        list.add("aaa1");list.add("bbb3");list.add("ccc");
        list.add("bbb2");list.add("ddd1");

        List<String> list2=readyData();
        calcTimeByOrder(list2);
        calcTimeByParallel(list2);

        try {
            parallelStreamInnerTheory();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 1.数据准备
     * @return
     */
    public static List<String> readyData(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        return values;
    }

    /**
     * 计算串行排序时间
     * @param values
     */
    public static void calcTimeByOrder(List<String> values){
        System.out.println("排序前"+values);
        long t0 = System.nanoTime();
        values.stream().sorted().forEach( System.out::println );
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sortFind took: %d ms", millis));
    }



    /**
     * 计算并行排序时间
     * 并行排序的结果还是有问题
     * @param values
     */
    public static void calcTimeByParallel(List<String> values){
        System.out.println("排序前"+values);
        long t0 = System.nanoTime();
        values.parallelStream().sorted().forEach( System.out::println );
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sortFind took: %d ms", millis));
    }


    /**
     * 测试parallelStream的内部原理
     * jdk1.7中的ForkJoin框架--分治法，工作窃取算法，双端队列，线程数==cpu数量
     * ThreadPoolExecutor用来做参考
     */
    public static void parallelStreamInnerTheory() throws InterruptedException {
        System.out.println("Hello World!");
        // 构造一个10000个元素的集合
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }

        // 统计并行执行list的线程
        Set<Thread> threadSet = new CopyOnWriteArraySet<>();
        // 并行执行
        list.parallelStream().forEach(integer -> {
            Thread thread = Thread.currentThread();
            threadSet.add(thread);
        });
        System.out.println("threadSet一共有" + threadSet.size() + "个线程");
        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");


        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list1.add(i);
            list2.add(i);
        }
        Set<Thread> threadSetTwo = new CopyOnWriteArraySet<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread threadA = new Thread(() -> {
            list1.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });
        Thread threadB = new Thread(() -> {
            list2.parallelStream().forEach(integer -> {
                Thread thread = Thread.currentThread();
                threadSetTwo.add(thread);
            });
            countDownLatch.countDown();
        });

        threadA.start();
        threadB.start();
        countDownLatch.await();
        System.out.print("threadSetTwo一共有" + threadSetTwo.size() + "个线程");

        System.out.println("---------------------------");
        System.out.println(threadSet);
        System.out.println(threadSetTwo);
        System.out.println("---------------------------");
        threadSetTwo.addAll(threadSet);
        System.out.println(threadSetTwo);
        System.out.println("threadSetTwo一共有" + threadSetTwo.size() + "个线程");
        System.out.println("系统一个有"+Runtime.getRuntime().availableProcessors()+"个cpu");

    }

}
