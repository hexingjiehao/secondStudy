package javaKnowledge.designPattern.action.template;

import javaKnowledge.designPattern.action.strategy.AbstractCalc;
import javaKnowledge.designPattern.action.strategy.Plus;

/**
 * Created by xiongjie on 2018/11/18.
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
