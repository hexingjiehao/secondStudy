package core.designPattern.structure.decorator;

import core.designPattern.create.simpleFactory.CommonInterface;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Source implements CommonInterface {

    @Override
    public void sayHello() {
        System.out.println("装饰者模式：被装饰者");
    }
}
