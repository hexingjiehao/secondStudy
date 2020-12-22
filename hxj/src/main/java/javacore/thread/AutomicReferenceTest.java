package javacore.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by xiongjie on 2018/10/29.
 * 原子更新引用类型，cas算法比较的地址，和原先比较
 */
public class AutomicReferenceTest {
    public static AtomicReference<User> atomicReference=new AtomicReference<User>();

    public static void main(String[] args){

        User user=new User("xiongjie",18);
        User user2=new User("xiongjie",18);
        atomicReference.set(user);
        User nuser=new User("Heixngjie",22);
        atomicReference.compareAndSet(user2,nuser);
        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getOld());

    }

    static class User{
        private String name;
        public  volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }

}
