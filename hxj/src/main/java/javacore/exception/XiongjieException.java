package javacore.exception;

/**
 * Created by xiongjie on 2018/10/17.
 * 这是一个自定义的检查型异常，必须要处理
 * 创建检查性异常类，继承 Exception 类。
 * 创建运行时异常类，继承 RuntimeException 类。
 */
public class XiongjieException extends Exception{

    //此处的amount用来储存当出现异常（取出钱多于余额时）所缺乏的钱
    private double amount;

    public XiongjieException(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
