package javacore.jvm实战;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: javacore.jvm实战
 * @className TestJVMAdjustGood
 * @description: 代码测试jvm调优
 * @author: xj
 * @create: 2020-12-22 23:10:52
 **/
public class TestJVMAdjustGood {

    /**
     * @Description 在祁东时设置虚拟机参数
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=C:\tmp\jvm.dump （生成堆内存相关的文件）
     * -XX:+PrintGCDetails （打印GC的详细过程）
     * -Xms10M （初始堆大小）
     * -Xmx10M  （最大堆大小）
     *
     * 讲生成的dump文件使用jdk/bin/jvisualvm.exe中进行查看
     *
     * @status done
     * @methodName
     * @param
     * @return
     * @Author xj
     * @Date 2020/12/22 23:12
     **/
    public static void main(String[] args) {
        //这个代码会导致内存泄漏
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 100000000; i++) {
            list.add(new byte[1024*1024]);
        }

    }
}
