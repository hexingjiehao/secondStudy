package algorithm;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * Created by xiongjie on 2018/11/13.
 * 实现逆波兰表达式，算数运算结果
 */
public class CalcExpress {

    //给定有序后缀表达式，计算出结果
    public static void main(String[] args){
        char[] arr=new char[]{'2','+','3','/','5'};
        calcExpress(arr);
        char[] arr2=new char[]{'2','+','3','-','4','*','6'};
        calcExpress(arr2);

        java.security.MessageDigest s;
    }

    public static void calcExpress(char[] arr){
        int result=0;
        Stack numStack=new Stack();
        Stack opStack=new Stack();
        for(char s:arr){
            if(s>='0' && s<='9'){
                //这样只能处理个位数的操作
                numStack.push(Integer.parseInt(s+""));
            }else if(opStack.isEmpty() ){
                opStack.push(s);
            }else{
                if(s=='+' || s=='-'){
                    caclByTwoStack(numStack,opStack);
                    opStack.push(s);
                }else{
                    char op= (char) opStack.pop();
                    if(op=='+' || op=='-'){
                        opStack.push(op);
                        opStack.push(s);
                    }else{
                        caclByTwoStack(numStack,opStack);
                        opStack.push(s);
                    }
                }
            }
        }
        while(!opStack.isEmpty()){
            char op= (char) opStack.pop();
            int num2= (int) numStack.pop();
            int num1= (int) numStack.pop();
            if(op=='+'){ numStack.push(num1+num2); }
            if(op=='-'){ numStack.push(num1-num2); }
            if(op=='*'){ numStack.push(num1*num2); }
            if(op=='/'){ numStack.push(num1/num2); }
        }
        result= (int) numStack.pop();
        System.out.println(result);
    }

    public static void caclByTwoStack(Stack numStack,Stack opStack){
        char op= (char) opStack.pop();
        int num2= (int) numStack.pop();
        int num1= (int) numStack.pop();
        if(op=='+'){ numStack.push(num1+num2); }
        if(op=='-'){ numStack.push(num1-num2); }
        if(op=='*'){ numStack.push(num1*num2); }
        if(op=='/'){ numStack.push(num1/num2); }
    }


}
