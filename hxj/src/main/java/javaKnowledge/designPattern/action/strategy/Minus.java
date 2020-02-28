package javaKnowledge.designPattern.action.strategy;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Minus extends AbstractCalc implements Cacl {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"\\-");
        return arrayInt[0]-arrayInt[1];
    }

    public static void main(String[] args) {
        String exp = "2+8";
        Cacl cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);
    }

    @Override
    public int calculate(int num1, int num2) {
        return num1-num2;
    }
}
