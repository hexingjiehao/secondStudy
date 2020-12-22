package javacore.designPattern.create.simpleFactory;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class CommonFactory {

    public CommonInterface build(String type){
        CommonInterface res=null;
        if( "china".equals(type) ){
            res=new ChinaHello();
        }else if("japan".equals(type)) {
            res=new JapanHello();
        }
        return res;
    }

}
