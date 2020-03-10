package core.thread;//package com.xiongjie.secondStudy.thread;
//
//
//import io.vertx.core.CompositeFuture;
//import io.vertx.core.Future;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * 测试vertx的多个future组合使用
// * <p>
// * CompositeFuture的功能：等待多个异步结果执行完毕
// * compose()的功能：等待上一个future结果，传递到下一个future使用。使future按照顺序执行
// */
//public class ManyFuture {
//
//    public static void main(String[] args) {
//        useCompositeFuture();
//        useCompose();
//        composeAndCompositeFuture();
//        moniFutureList1();
//        moniFutureList2();
//    }
//
//    public static void useCompositeFuture() {
//        Future<Integer> f1 = getAdd(1);
//        Future<Integer> f2 = getAdd(2);
//
//        CompositeFuture.all(f1, f2).setHandler(ar -> {
//            if (ar.succeeded()) {
//                System.out.println(f1.result() + f2.result());
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                System.out.println("bad");
//            }
//        });
//        System.out.println("hello");
//    }
//
//    public static Future<Integer> getAdd(int num) {
//        Future<Integer> future = Future.future();
//        future.complete(num);
//        return future;
//    }
//
//    /**
//     * 集合可以在compose中添加数据，但是基本类型不能在compose中修改数值
//     */
//    public static void useCompose() {
//        List<String> list = new ArrayList<>();
//        Future.succeededFuture()
//                .compose(v1 -> {
//                    Future<Integer> f1 = Future.future();
//                    f1.complete(1);
//                    list.add("1");
//                    return f1;
//                })
//                .compose(v2 -> {
//                    System.out.println(v2);
//                    Future<Integer> f2 = Future.future();
//                    f2.complete(v2 + 2);
//                    return f2;
//                })
//                .compose(v3 -> {
//                    System.out.println(v3);
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return Future.succeededFuture();
//                });
//        System.out.println("world");
//        System.out.println(list);
//    }
//
//    public static void composeAndCompositeFuture() {
//        Future<Integer> f1 = getAdd(1);
//        Future<Integer> f2 = getAdd(2);
//
//        CompositeFuture.all(f1, f2).setHandler(ar -> {
//            if (ar.succeeded()) {
//                System.out.println(f1.result() + f2.result());
//
//                List<String> list = new ArrayList<>();
//                Future.succeededFuture()
//                        .compose(v1 -> {
//                            Future<Integer> f3 = Future.future();
//                            f3.complete(3);
//                            list.add("3");
//                            return f3;
//                        })
//                        .compose(v2 -> {
//                            System.out.println(v2);
//                            Future<Integer> f4 = Future.future();
//                            f4.complete(v2 + 4);
//                            list.add("4");
//                            return f4;
//                        })
//                        .compose(v3 -> {
//                            System.out.println(v3);
//                            return Future.succeededFuture();
//                        });
//                System.out.println("morning");
//                System.out.println(list);
//
//            } else {
//                System.out.println("bad");
//            }
//        });
//        System.out.println("good");
//    }
//
//
//    public static void moniFutureList1() {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//
//        List<Future> futureList = new ArrayList<>();
//        for (int value : list) {
//            Future<Integer> f = getAdd(value);
//            futureList.add(f);
//        }
//
//        CompositeFuture.all(futureList).setHandler(ar -> {
//            int sum = 0;
//            for (int i = 0; i < futureList.size(); i++) {
//                int tmp = (int) futureList.get(i).result();
//                sum += tmp;
//            }
//            System.out.println("模拟futureList=" + sum);
//        });
//    }
//
//    public static void moniFutureList2() {
//        List<Integer> list = new ArrayList<>();
//        list.add(4);
//        list.add(5);
//        list.add(6);
//
//        List<Integer> sumList = new ArrayList<>();
//
//        Future future = Future.succeededFuture();
//        for (int value : list) {
//            future.compose(ar -> {
//                Future<Integer> f = getAdd(value);
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                sumList.add(f.result());
//                return f;
//            });
//            System.out.println("for=" + value);
//        }
//
//        System.out.println(sumList);
//
//        future.setHandler(ar -> {
//            int sum = 0;
//            for (int value : sumList) {
//                sum += value;
//            }
//            System.out.println(sum);
//        });
//    }
//}
