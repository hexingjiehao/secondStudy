package com.xiongjie;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.unit.*;
import io.vertx.ext.unit.junit.*;
import io.vertx.ext.unit.report.ReportOptions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class MyVertxTest{

    @Test
    public void miniTestSuite(){
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字", context -> {
            String s = "value";
            context.assertEquals("value", s,"s的期望值是value,实际值是"+s);
        });
        suite.run();  //通过/失败 都不显示测试结果
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void chainSuiteTest(){
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字:1", context -> {
            String s = "hello";
            System.out.println("第一个测试用例的值："+s);
            context.assertEquals("hello", s);
        }).test("测试案例名字:2",context -> {
            String s = "world";
            System.out.println("第二个测试用例的值："+s);
            context.assertEquals("world", s);
        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void beforeAfterSuiteTest(){
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字:1", context -> {
            String s = "hello";
            System.out.println("第一个测试用例的值："+s);
            context.assertEquals("hello", s);
        }).test("测试案例名字:2",context -> {
            String s = "world";
            System.out.println("第二个测试用例的值："+s);
            context.assertEquals("world", s);
        }).after(context -> {
            System.out.println("所有测试案例执行完成！");
        }).before(context ->{
            System.out.println("开始执行测试案例");
        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void eachBeforeAfterSuiteTest(){
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.beforeEach(context ->{
            System.out.println("开始！！！");

        }).test("测试案例名字:1", context -> {
            String s = "hello";
            System.out.println("第一个测试用例的值："+s);
            context.assertEquals("hello", s);

        }).test("测试案例名字:2",context -> {
            String s = "world";
            System.out.println("第二个测试用例的值："+s);
            context.assertEquals("world", s);

        }).afterEach(context -> {
            System.out.println("结束！！！");

        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void assertCollectionTest(){
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字:assertNotEquals", context -> {
            String s = "hello";
            context.assertNotEquals("hello1", s);

        }).test("测试案例名字:assertNull",context -> {
            System.out.println("断言空的参数值是：null");
            String s = null;
            context.assertNull(s);

        }).test("测试案例名字:assertNotNull",context -> {
            System.out.println("断言不空的参数值是：任意不是null的值");
            String s = "not null";
            context.assertNotNull(s);

        }).test("测试案例名字:assertInRange",context -> {
            System.out.println("断言0.1在 0.2 +/- 0.5的范围");
            double s = 0.1;
            context.assertInRange(s,0.2,0.5);

        }).test("测试案例名字:assertTrue",context -> {
            boolean s = true;
            context.assertTrue(s);

        }).test("测试案例名字:assertFalse",context -> {
            int s = 5;
            context.assertFalse(s>10);

        }).test("测试案例名字:Failing",context -> {
            try {
                //这是人为抛出错误，即使捕获也会打印红字错误
//                context.fail("测试断言的Failing方法");
            }catch (Error e) {
                System.out.println("😂");
            }

        }).test("测试案例名字:第三方断言框架",context -> context.verify(v -> {
            int s=10;
            Assert.assertNotNull("not null!");
            Assert.assertEquals(10, s);
        }));
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void asynchronousTest(){

        Vertx vertx=Vertx.vertx();

        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字", context -> {
            Async async = context.async();
            vertx.eventBus().consumer("the-address", msg -> {

                //需要其他消息来触发，比如访问页面，发送消息等。导致整个测试卡住，控制台不会打印测试通过等消息，套件运行也不会结束

                System.out.println(1);
                System.out.println("来自总线的事件回调终止测试");
                System.out.println(2);
                async.complete();
                System.out.println(3);
            });

            System.out.println("回调存在但是测试没有终止");
        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果

    }

    @Test
    public void multiAsynchronousTest() {

        Vertx vertx = Vertx.vertx();

        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字", context -> {

            Async async1 = context.async();
            HttpClient client = vertx.createHttpClient();
            HttpClientRequest req = client.get(8080, "localhost", "/");
            req.exceptionHandler(err -> context.fail(err.getMessage()));
            req.handler(resp -> {
                context.assertEquals(200, resp.statusCode());
                async1.complete();
            });
            req.end();

            Async async2 = context.async();
            vertx.eventBus().consumer("the-address", msg -> {
                async2.complete();
            });
        });
        //需要通过访问页面来触发回调测试。
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果

    }

    @Test
    public void asynObjectBeforeCallBack(){

        Vertx vertx = Vertx.vertx();

        //在回调测试前。首先验证网页是否能够正常访问
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.before(context -> {
            Async async = context.async();
            HttpServer server = vertx.createHttpServer();
            server.requestHandler( rep->{
                System.out.println(rep.host());
            });
            server.listen(8080, ar -> {
                context.assertTrue(ar.succeeded());
                async.complete();
            });

            async.awaitSuccess();

        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void multiAsync(){

        Vertx vertx = Vertx.vertx();

        //在回调测试前。首先验证网页是否能够正常访问
        TestSuite suite = TestSuite.create("测试套件名字");
        suite.before(context -> {
            Async async = context.async(2);
            HttpServer server = vertx.createHttpServer();
            server.requestHandler(rep->{
                System.out.println(rep.host());
            });

            server.listen(8080, ar -> {
                context.assertTrue(ar.succeeded());
                async.countDown();
            });

            vertx.setTimer(1000, id -> {
                async.complete();
            });

            async.awaitSuccess();

        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果

    }

    @Test
    public void repeatingTest(){

        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字", 10, context -> {
            System.out.println("hello");
        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果
    }

    @Test
    public void sharingObjectTest(){

        Vertx vertx = Vertx.vertx();

        TestSuite suite = TestSuite.create("测试套件名字");
        suite.before(context -> {
            context.put("host", "localhost");
        }).beforeEach(context -> {

            int port = (int)(Math.random()*65535)+1;
            String host = context.get("host");

            Async async = context.async();
            HttpServer server = vertx.createHttpServer();
            server.requestHandler(req -> {
                req.response().setStatusCode(200).end();
            });
            server.listen(port, host, ar -> {
                context.assertTrue(ar.succeeded());
                context.put("port", port);
                async.complete();
            });

        }).test("测试案例名字", context -> {

            int port = context.get("port");
            String host = context.get("host");


            HttpClient client = vertx.createHttpClient();
            HttpClientRequest req = client.get(port, host, "/resource");
            Async async = context.async();
            req.handler(resp -> {
                context.assertEquals(200, resp.statusCode());
                async.complete();
            });
            req.end();
        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果

    }

    @Test
    public void runningVertxTest(){

        Vertx vertx = Vertx.vertx();
        TestSuite suite = TestSuite.create("测试套件名字");

        TestCompletion completion = suite.run(vertx);
        completion.handler(ar -> {
            if (ar.succeeded()) {
                System.out.println("测试套件通过");
            } else {
                System.out.println("测试套件失败");
                ar.cause().printStackTrace();
            }
        });

    }

    @Test
    public void reportingTest(){

        ReportOptions consoleReport = new ReportOptions().setTo("console");

        TestSuite suite = TestSuite.create("测试套件名字");
        suite.test("测试案例名字",context->{
            System.out.println("测试报告");
        } );
        suite.run(new TestOptions().addReporter(consoleReport));

    }

    @Test
    public void exceptionCatch(){

        Vertx vertx = Vertx.vertx();
        TestSuite suite = TestSuite.create("测试套件名字");

        suite.before(testContext -> {
            vertx.exceptionHandler(testContext.exceptionHandler());
        });

        suite.test("测试案例名字", testContext -> {
            HttpServer server = vertx.createHttpServer().requestHandler(req -> {
                if (req.path().equals("/somepath")) {
                    throw new AssertionError("Wrong path!");
                }
                req.response().end();
            });
        });
        suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console"))); //在控制台显示测试结果

    }

    @Test
    public void junitIntegrationTest(TestContext context){
        //参数自动注入，在类头增加注解后@RunWith(VertxUnitRunner.class)
        context.assertFalse(false);
    }

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();
    @Test
    public void RunningOnVertx(TestContext context) {
        Vertx vertx = rule.vertx();
        System.out.println("rule......");
    }


    @Rule
    public Timeout timerules = Timeout.seconds(1);
    @Test(timeout = 1000l)
    public void timeoutTest(TestContext context) {
        System.out.println("测试超时");
    }

    /**
     * 必须要联合使用
     */
    @Rule
    public RepeatRule repeatRule = new RepeatRule();
    @Repeat(10)
    @Test
    public void annotationRepeat(){
        System.out.println("world");
    }
}
