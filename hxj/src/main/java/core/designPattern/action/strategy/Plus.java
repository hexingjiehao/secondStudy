package core.designPattern.action.strategy;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Plus extends AbstractCalc implements Cacl {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"\\+");
        return arrayInt[0]+arrayInt[1];
    }

    @Override
    public int calculate(int num1, int num2) {
        return num1+num2;
    }
}
