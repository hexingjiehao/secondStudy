package javaKnowledge.exception;

/**
 * Created by xiongjie on 2018/10/17.
 */
public class BankDemo {

    public static void main(String [] args)
    {
        CheckingAccount c = new CheckingAccount(101);
        Object o=new Object();
        System.out.println("Depositing $500...");
        c.deposit(500.00);
        try {
            System.out.println("Withdrawing $100...");
            c.withdraw(100.00);
            System.out.println("Withdrawing $600...");
            c.withdraw(600.00);
        }catch(XiongjieException e) {
            System.out.println("Sorry, but you are short $" + e.getAmount());
            e.printStackTrace();
        }
    }

}
