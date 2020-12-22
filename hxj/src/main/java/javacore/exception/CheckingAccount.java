package javacore.exception;

/**
 * Created by xiongjie on 2018/10/17.
 */
public class CheckingAccount {

    //balance为余额，number为卡号
    private double balance;
    private int number;
    public CheckingAccount(int number) {
        this.number = number;
    }

    //方法：存钱
    public void deposit(double amount) {
        balance += amount;
    }

    //方法：取钱
    public void withdraw(double amount) throws XiongjieException {
        if(amount <= balance) {
            balance -= amount;
        } else {
            double needs = amount - balance;

            //逻辑异常,抛出自定义异常
            throw new XiongjieException(needs);
        }
    }

    //方法：返回余额
    public double getBalance() {
        return balance;
    }

    //方法：返回卡号
    public int getNumber() {
        return number;
    }
}
