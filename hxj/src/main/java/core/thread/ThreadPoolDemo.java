package core.thread;

import java.util.concurrent.*;

/**
 * Created by xiongjie on 2018/10/29.
 * 测试线程池的使用过程
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        //1.创建线程池,7个主要参数
        ThreadPoolExecutor pool=new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(30), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return null;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

            }
        });

        //2.向线程池添加任务
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("无返回值的任务执行");
            }
        }); //无返回值


        FutureTask<Object> futureTask= (FutureTask<Object>) pool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("有返回值的任务执行");
            }
        }); //有返回值

        //超时获取
        System.out.println(futureTask.get(1,TimeUnit.SECONDS));

        //3.关闭线程池
        pool.shutdown();

    }

}
