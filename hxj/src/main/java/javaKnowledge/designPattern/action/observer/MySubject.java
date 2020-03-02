package javaKnowledge.designPattern.action.observer;

/**
 * Created by xiongjie on 2018/11/18.
 * 本质：一个接口，将另一个接口作为方法参数进行处理
 *      然后，将这个接口变成抽象类，最后，子类继承该抽象类，并实现操作方法
 */
public class MySubject extends AbstractSubject {

    @Override
    public void operation() {
        System.out.println("观察者模式：更新自己");
        notifyObservers();
    }

    public static void main(String[] args) {
        Subject sub = new MySubject();
        Observer os1=new Observer1();
        Observer os2=new Observer2();
        sub.add(os1);
        sub.add(os2);
        sub.operation();

        sub.del(os1);
        sub.operation();
    }

}