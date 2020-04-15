package core.designPattern.structure.adapter;

/**
 * Created by xiongjie on 2018/11/18.
 *
 * 使用重写代替接口实现
 */
public class InterfaceAdapter extends AbstractAdapter {

    @Override
    public void sayHello() {
        System.out.println("接口-适配器模式：子类方法Hello");
    }


    public static void main(String[] args){
        InterfaceAdapter res=new InterfaceAdapter();
        res.sayHello();
        res.sayWorld();
    }

}
