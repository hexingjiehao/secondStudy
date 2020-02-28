package javaKnowledge.concurrent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiongjie on 2018/11/14.
 * 实现高并发的数据插入----使用jdbc实现
 * 同时，保证数据不重复，效率提高
 */
public class DataAdd {

    public static void main(String[] args){
        List<Student> list=new ArrayList<>();
        for(int i=0;i<10000;i++){
            list.add( new Student(i,2*i,"name="+(3*i) ) );
        }
        try {
            long time=System.currentTimeMillis();
            cuncurInsert(list);
            System.out.println("高并发执行成功,数据量"+list.size()+"花费时间："+(System.currentTimeMillis()-time)/1000+"秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //开启多线程执行高并发插入操作
    public static void cuncurInsert(List<Student> list) throws InterruptedException {
        int listSize = list.size();        //数据集合大小
        int count = 1000;  //单个线程的执行数量
        int runSize = (listSize/count)+1;  //开启的线程数

        List<Student> newlist = null;       //存放每个线程的执行数据
        ExecutorService executor = Executors.newFixedThreadPool(runSize);      //创建一个线程池，数量和开启线程的数量一样

        //创建两个个计数器
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runSize);
        //循环创建线程
        for (int i = 0; i < runSize ; i++) {
            //计算每个线程执行的数据
            if((i+1)==runSize){
                int startIndex = (i*count);
                int endIndex = list.size();
                newlist= list.subList(startIndex, endIndex);
            }else{
                int startIndex = (i*count);
                int endIndex = (i+1)*count;
                newlist= list.subList(startIndex, endIndex);
            }
            //线程类
            XiongjieThread mythead = new XiongjieThread(newlist,begin,end);
            //这里执行线程的方式是调用线程池里的executor.execute(mythead)方法。
            executor.execute(mythead);
        }

        begin.countDown();  //确保main线程到了这里，多线程才开始执行....也许有总要逻辑需要处理才能进行下一步
        end.await();   //等待所有多线程执行完毕，main线程才关闭线程池
        //执行完关闭线程池
        executor.shutdown();
    }

    static class XiongjieThread implements Runnable{

        private List<Student> list;
        private CountDownLatch begin;
        private CountDownLatch end;

        public XiongjieThread(List<Student> list, CountDownLatch begin, CountDownLatch end) {
            this.list=list;
            this.begin=begin;
            this.end=end;
        }

        @Override
        public void run() {
            try {
                //开始准备插入数据库
//                String URL="jdbc:mysql://127.0.0.1:3306/guns?useUnicode=true&amp;characterEncoding=utf-8";
//                Class.forName("com.mysql.jdbc.Driver");

                //5.6以后的mysql版本驱动--需要配置服务器上的时区，UTC时标准时区，Asia/Shanghai上海时中国时区
                String URL="jdbc:mysql://127.0.0.1:3306/guns?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";
                Class.forName("com.mysql.cj.jdbc.Driver");

                String USER="root";
                String PASSWORD="root";


                Connection conn= DriverManager.getConnection(URL, USER, PASSWORD);
                String sql = "INSERT IGNORE INTO concurrentadd (code,name) VALUES (?,?)";

                    conn.setAutoCommit(false);
                    PreparedStatement pst = conn.prepareStatement(sql);
                    for (Student stu:list) {
                        pst.setInt(1, stu.code);
                        pst.setString(2,stu.name);
                        pst.addBatch();
                    }
                    pst.executeBatch();
                    conn.commit();

                    pst.close();
                    conn.close();


                //执行完让线程直接进入等待
                begin.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //计数器减一
                end.countDown();
            }
        }
    }

    static class Student{
        int id;
        int code;
        String name;

        public Student(int id, int code, String name) {
            this.id = id;
            this.code = code;
            this.name = name;
        }
    }

}
