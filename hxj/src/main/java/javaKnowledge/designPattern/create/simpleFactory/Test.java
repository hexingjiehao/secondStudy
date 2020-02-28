package javaKnowledge.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Test {

    public static void main(String[] args){
        //测试普通简单工厂
        CommonFactory factory=new CommonFactory();
        CommonInterface obj=factory.build("china");
        obj.sayHello();

        //测试多方法普通简单工厂
        ManyMethodFactory methodFactory=new ManyMethodFactory();
        obj=methodFactory.buildJapan();
        obj.sayHello();

        //测试多个静态方法普通简单工厂
        obj=ManyStaticMethodFactory.buildChina();
        obj.sayHello();

        //测试工厂方法模式，创建多个工厂
        FactoryMethod factoryMethod=new FactoryMethod();
        obj=factoryMethod.produce();
        obj.sayHello();
    }

}
