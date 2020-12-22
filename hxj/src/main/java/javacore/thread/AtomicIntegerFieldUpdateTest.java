package javacore.thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by xiongjie on 2018/10/29.
 * 测试
 */
public class AtomicIntegerFieldUpdateTest {

    private static AtomicIntegerFieldUpdater a= AtomicIntegerFieldUpdater.newUpdater(AutomicReferenceTest.User.class,"old");

    public static void main(String[] args){
        AutomicReferenceTest.User  user=new AutomicReferenceTest.User("xiongjie",18);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));

        //属性调用对象获取值,必须是public,而且必须是volatile原子属性
        a.set(user,25);
        System.out.println(a.get(user));

    }

}
