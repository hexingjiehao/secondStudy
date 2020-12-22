package javacore.Collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiongjie on 2018/10/17.
 */
public class XiongjieCollection {

    public static void main(String[] args){

        //下面的都是线程安全的,Collections目前只能将List,Set,Map包装成线程安全
        //Collections实现线程安全的方法：实现好像是单例模式
        List<String> lists= Collections.synchronizedList(new ArrayList<String>());
        Map map=Collections.synchronizedMap(new HashMap());
        Map concurrentHashMap = new ConcurrentHashMap();
        Set set=Collections.synchronizedSet(new HashSet());
        Map mapTable=new Hashtable(); //这个已经不建议使用了

        int a=3^2; //异或操作
        a=1>>>16;
        int b=3&2;  //位与运算符
        System.out.println(b);



    }

}
