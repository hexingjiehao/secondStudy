package Vertx.vertxShell;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.Option;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.shell.*;
import io.vertx.ext.shell.command.CommandBuilder;
import io.vertx.ext.shell.command.CommandRegistry;
import io.vertx.ext.shell.command.CommandResolver;
import io.vertx.ext.shell.session.Session;
import io.vertx.ext.shell.term.*;
import io.vertx.ext.web.Router;

public class ExtendVertxShell {

    static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        shellServiceViaTelnet();
        userShellServer();
        userShellServerWithPseudoTerminal();
    }

    public static void shellServiceViaTelnet() {
        vertx.deployVerticle(ShellVerticle::new,
                new DeploymentOptions().setConfig(
                        new JsonObject().put("telnetOptions",
                                new JsonObject().
                                        put("host", "localhost").
                                        put("port", 4000))
                ), ar -> {
                    if (ar.succeeded()) {
                        System.out.println(ar.result());
                        addExtend();
                    } else {
                        System.out.println(ar.cause().toString());
                    }
                }
        );
    }

    public static void addExtend(){
        extendHelloWorld();
        extendPrintArgs();
        extendCli();
        extendCliHelp();
        ioTerminal();
        extendShellSession();
        extendProcessEnd();
        extendInterruptEvent();
        extendResizeEvent();
        extendStdinEvent();
        extendSuspendAndResume();
        extendEndEvent();
    }

    public static void extendHelloWorld() {
        CommandBuilder builder = CommandBuilder.command("helloworld");
        builder.processHandler(process -> {
            process.write("Hello World");
            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendPrintArgs() {
        CommandBuilder builder = CommandBuilder.command("printArgs");
        builder.processHandler(process -> {
            for (String arg : process.args()) {
                process.write("Argument " + arg);
            }
            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    /**
     * 具体代码举例
     * cli "hello" -myOption=world
     * 结果：
     *  The argument is hello and the option is world
     */
    public static void extendCli() {
        CLI cli = CLI.create("cli").
                addArgument(new Argument().setArgName("myArg")).
                addOption(new Option().setShortName("m").setLongName("myOption"));

        CommandBuilder command = CommandBuilder.command(cli);
        command.processHandler(process -> {
            CommandLine commandLine = process.commandLine();
            String argValue = commandLine.getArgumentValue(0);
            String optValue = commandLine.getOptionValue("myOption");
            process.write("The argument is " + argValue + " and the option is " + optValue);

            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(command.build(vertx));
    }

    public static void extendCliHelp() {
        CLI cli = CLI.create("cliHelp").
                addArgument(new Argument().setArgName("my-arg")).
                addOption(new Option().setArgName("help").setShortName("h").setLongName("help"));

        CommandBuilder command = CommandBuilder.command(cli);
        command.processHandler(process -> {
            CommandLine commandLine = process.commandLine();
            String argValue = commandLine.getArgumentValue(0);
            String optValue = commandLine.getOptionValue("help");
            process.write("The argument is " + argValue + " and the option is " + optValue);

            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(command.build(vertx));
    }

    public static void ioTerminal() {
        CLI cli = CLI.create("tty");

        CommandBuilder builder = CommandBuilder.command(cli);
        builder.processHandler(tty -> {
            tty.write("Hello World\n");
            tty.write("Current terminal size: (" + tty.width() + ", " + tty.height() + ")\n");
            System.out.println("terminal type : " + tty.type());
            tty.end();
        });

        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendShellSession() {
        CommandBuilder builder = CommandBuilder.command("shellSession");
        builder.processHandler(process -> {
            Session session = process.session();
            if (session.get("my_key") == null) {
                process.write("1");
                //每次输入命令时，session都会清空
                session.put("my key", "my value");
                process.write(session.get("my key")+"\n");;
            }
            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendProcessEnd() {
        CommandBuilder builder = CommandBuilder.command("p-end");
        builder.processHandler(process -> {
            Vertx vertx = process.vertx();

            vertx.setTimer(1000, id -> {
                process.write("1秒钟睡眠完成");
                process.end();
            });
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendInterruptEvent() {
        CommandBuilder builder = CommandBuilder.command("interrupt");
        builder.processHandler(process -> {
            Vertx vertx = process.vertx();

            // 每秒打印一个消息
            long periodicId = vertx.setPeriodic(1000, id -> {
                process.write("tick\n");
            });

            // 按Ctrl+C: 取消定时器和进程
            process.interruptHandler(v -> {
                vertx.cancelTimer(periodicId);
                process.write("定时器取消，定时器的ID="+periodicId);
                process.end();
            });
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendResizeEvent() {
        CommandBuilder builder = CommandBuilder.command("resize");
        builder.processHandler(process -> {
            //通过移动控制台窗口大小，在控制台打印信息
            process.resizehandler(v -> {
                process.write("terminal resized : " + process.width() + " " + process.height());
                process.end();
            });
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendStdinEvent() {
        CommandBuilder builder = CommandBuilder.command("stdin");
        builder.processHandler(process -> {
            //每次接收键盘上的一个字符
            process.stdinHandler(data -> {
                process.write("Received " + data);
                process.end();
            });
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendSuspendAndResume() {
        CommandBuilder builder = CommandBuilder.command("susp-resu");
        builder.processHandler(process -> {
            //当输入Ctrl+Z时，触发挂起事件
            process.suspendHandler(v -> {
                System.out.println("Suspended");
            });

            //当输入 fg时，触发恢复事件
            //这个事件比较特殊，即使该命令结束，也会接收到恢复事件,和fg事件搭配使用
            process.resumeHandler(v -> {
                process.write("Resumed");
                process.end();
            });
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void extendEndEvent() {
        CommandBuilder builder = CommandBuilder.command("end");
        builder.processHandler(process -> {
            // 调用process.end()触发事件
            process.endHandler(v -> {
                System.out.println("Terminated");
            });
            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    //将ShellServer绑定在已经存在的vertx路由上
    public static void userShellServer() {
        ShellServer server = ShellServer.create(vertx);

        Router router= Router.router(vertx);
        Router shellRouter = Router.router(vertx);
        router.mountSubRouter("/shell", shellRouter);
        TermServer httpTermServer = TermServer.createHttpTermServer(vertx, router);

        server.registerTermServer(httpTermServer);
        server.registerCommandResolver(CommandResolver.baseCommands(vertx));
        server.listen();

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

    //默认有一个/shell的主页
    public static void userShellServerWithPseudoTerminal() {
        TermServer server = TermServer.createHttpTermServer(vertx, new HttpTermOptions().setPort(5000).setHost("localhost"));
        server.termHandler(term -> {
            term.stdinHandler(line -> {
                term.write(line);
            });
        });
        server.listen();
    }
}
