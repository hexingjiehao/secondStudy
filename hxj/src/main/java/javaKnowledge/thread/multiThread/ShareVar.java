package javaKnowledge.thread.multiThread;

/**
 * Created by xiongjie on 2018/11/3.
 */
public class ShareVar extends Thread{

    @Override
    public void run() {

        //线程名是传入的线程对象参数或者正在调用的线程
        System.out.println("name="+Thread.currentThread().getName());
        //线程名是创建的类对象
        System.out.println("name="+this.getName());
    }

    public static void main(String[] args){

        ShareVar a=new ShareVar();
        Thread thread=new Thread(a,"A");
        thread.start();

    }

}
