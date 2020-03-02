package javaKnowledge.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> c = Class.forName("javaKnowledge.reflect.DemoReflectService");

        //单个参数的反射
        Method m = c.getMethod("sayHello", String.class);
        m.invoke(c.newInstance(),"hello");

        //多个参数的反射
        m = c.getMethod("sayHelloWorld", String.class, Integer.class);
        m.invoke(c.newInstance(),"hello world",5);
    }

}
