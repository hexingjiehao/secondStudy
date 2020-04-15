package core.thread;//package com.xiongjie.secondStudy.thread;
//
//import io.vertx.core.AsyncResult;
//import io.vertx.core.Future;
//import io.vertx.core.Handler;
//
///**
// * 测试参数是future的使用,这是vertx的特权
// */
//public class ParameterFuture {
//
//    public static void main(String[] args) {
//        test(1,2,ar->{
//            System.out.println("sum="+ar.result());
//        });
//    }
//
//    private static void test(int a,int b,Handler<AsyncResult<Integer>> resultHandler){
//        Future<Integer> future=Future.future();
//        Integer sum=a+b;
//        future.complete(sum);
//        resultHandler.handle(future);
//    }
//}
