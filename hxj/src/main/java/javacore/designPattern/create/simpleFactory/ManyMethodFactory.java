package javacore.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class ManyMethodFactory {

    public CommonInterface buildChina(){
        CommonInterface res=new ChinaHello();
        return res;
    }

    public CommonInterface buildJapan(){
        CommonInterface res=new JapanHello();
        return res;
    }

}
