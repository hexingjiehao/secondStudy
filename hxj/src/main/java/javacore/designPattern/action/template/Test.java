package javacore.designPattern.action.template;

import javacore.designPattern.action.strategy.AbstractCalc;
import javacore.designPattern.action.strategy.Plus;

/**
 * Created by xiongjie on 2018/11/18.
 * 本质是：同时继承抽象类和实现接口
 *        有点像变体般的switch
 */
public class Test {

    public static void main(String[] args){
        //测试模板方法模式--使用抽象类调用方法
        String exp="8+8";
        AbstractCalc calc=new Plus();
        int res=calc.calculate(exp,"\\+");
        System.out.println(res);
    }

}
