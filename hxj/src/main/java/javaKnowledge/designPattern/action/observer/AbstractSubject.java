package javaKnowledge.designPattern.action.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by xiongjie on 2018/11/18.
 * 抽象类可以不实现接口方法
 */
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();
    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();
        }
    }



}

