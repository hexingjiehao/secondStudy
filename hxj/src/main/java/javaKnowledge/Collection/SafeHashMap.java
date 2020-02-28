package javaKnowledge.Collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiongjie on 2018/10/18.
 */
public class SafeHashMap {

    public final static int THREAD_POOL_SIZE = 5;

    public static Map<String, Integer> safeHashTable = null;
    public static Map<String, Integer> safeSynchronizedHashMap = null;
    public static Map<String, Integer> safeConcurrentHashMap = null;


    /**
     * 验证HashTable,
     * Collections.synchronizedMap(new HashMap<String, Integer>());
     * ConcurrentHashMap
     * 的get,put效率
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        Stack stack;

        // Test with Hashtable Object
        safeHashTable = new Hashtable<String, Integer>();
        calSafeHashMapTime(safeHashTable);

        // Test with synchronizedMap Object
        safeSynchronizedHashMap = Collections.synchronizedMap(new HashMap<String, Integer>());
        calSafeHashMapTime(safeSynchronizedHashMap);

        // Test with ConcurrentHashMap Object
        safeConcurrentHashMap = new ConcurrentHashMap<String, Integer>();
        calSafeHashMapTime(safeConcurrentHashMap);

    }


    /**
     * 多线程有中断的情况，最好抛出异常
     * @param map
     * @throws InterruptedException
     */
    public static void calSafeHashMapTime(final Map<String, Integer> map) throws InterruptedException {

        System.out.println("Test started for: " + map.getClass());
        long averageTime = 0;
        for (int i = 0; i < 5; i++) {

            long startTime = System.nanoTime();
            ExecutorService server = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                server.execute(new Runnable() {

                    @SuppressWarnings("unused")
                    @Override
                    public void run() {

                        for (int i = 0; i < 500000; i++) {
                            Integer randomNumber = (int) Math.ceil(Math.random() * 550000);

                            // Retrieve value. We are not using it anywhere
                            Integer Value = map.get(String.valueOf(randomNumber));

                            // Put value
                            map.put(String.valueOf(randomNumber), randomNumber);
                        }
                    }
                });
            }

            // Make sure executor stops
            server.shutdown();

            // Blocks until all tasks have completed execution after a shutdown request
            server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
        }
        System.out.println("For " + map.getClass() + " the average time is " + averageTime / 5 + " ms\n");
    }

}
