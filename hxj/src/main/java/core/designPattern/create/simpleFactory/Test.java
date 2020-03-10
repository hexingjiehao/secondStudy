package core.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Test {

    public static void main(String[] args){
        //测试普通简单工厂
        //本质是：根据传入类型，创建不同的实现类
        CommonFactory factory=new CommonFactory();
        CommonInterface obj=factory.build("china");
        obj.sayHello();

        //测试多方法普通简单工厂
        //本质是：直接添加一个实现类，就在工厂增加一个方法
        ManyMethodFactory methodFactory=new ManyMethodFactory();
        obj=methodFactory.buildJapan();
        obj.sayHello();

        //测试多个静态方法普通简单工厂
        //本质是：不创建工厂对象了，直接使用类静态方法
        obj=ManyStaticMethodFactory.buildChina();
        obj.sayHello();

        //测试工厂方法模式，创建多个工厂
        //本质是：在一个接口的实现类中，创建另一个接口的实现类
        FactoryMethod factoryMethod=new FactoryMethod();
        obj=factoryMethod.produce();
        obj.sayHello();
    }

}
