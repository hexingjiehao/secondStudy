package javaKnowledge.reflect;

public class DemoReflectService {

    /**
     * 单个参数的反射
     * @param hello
     */
    public void sayHello(String hello){
        System.out.println(hello);
    }

    public void sayHelloWorld(String hello,Integer number){
        System.out.println("你好="+hello+" 这是数字"+number);
    }


}
