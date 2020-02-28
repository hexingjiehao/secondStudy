package javaKnowledge.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class ManyStaticMethodFactory {

    public static CommonInterface buildChina(){
        CommonInterface res=new ChinaHello();
        return res;
    }

    public static CommonInterface buildJapan(){
        CommonInterface res=new JapanHello();
        return res;
    }

}
