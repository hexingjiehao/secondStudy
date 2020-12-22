package javacore.exception;

import java.rmi.RemoteException;

/**
 * Created by xiongjie on 2018/10/17.
 */
public class ViewException {

    public static void main(String[] args){
        DealruntimeException();
        System.out.println(DealExceptionReturn());
//        DealThrowsAndThrowException();  //必须处理被抛出异常
    }

    /**
     * 1.运行时异常处理后，try-catch的后续代码继续执行
     */
    public static void DealruntimeException(){
        try{
            int a[] = new int[2];
            System.out.println("Access element three :" + a[3]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Exception thrown  :" + e);
        }
        System.out.println("Out of the block");
    }

    /**
     * 2.捕获异常中有return时的代码执行顺序
     * 当try-catch-finally中都有return语句时
     * 最终返回的是finally中的return值
     *
     * try-catch是并列关系.当finally中没有return时
     * 代码依旧会执行，但是如果try-catch中已经return一个变量
     * 那么finally中对这个变量的处理都是无用的因为返回的值已经确定了
     */
    public static String DealExceptionReturn(){
        int tmp=0;
        try{
            tmp=1;
            System.out.println("tmp="+tmp);
            int a[] = new int[2];
            System.out.println("执行try :" + a[1]);
            return "try:"+tmp;
        }catch(ArrayIndexOutOfBoundsException e){
            tmp=2;
            System.out.println("tmp="+tmp);
            System.out.println("catch...  :" + e);
            return "catch:"+tmp;
        }finally {
            tmp=3;
            System.out.println("tmp="+tmp);
            System.out.println("执行finally...");
//            return "finally:"+tmp;
        }
    }

    /**
     * 3.throw抛出任意异常，在代码中任意位置
     * throws抛出制定异常，只能在方法头上，多个异常时用逗号分割
     * 调用这个方法时，必须处理这些异常或者继续向上抛出
     */
    public static void DealThrowsAndThrowException() throws RemoteException,
            ArrayIndexOutOfBoundsException{

        throw new RemoteException();
    }


}
