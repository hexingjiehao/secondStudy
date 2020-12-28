package Vertx.vertxShell;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.cli.Argument;
import io.vertx.core.cli.CLI;
import io.vertx.core.cli.CommandLine;
import io.vertx.core.cli.Option;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.auth.shiro.ShiroAuthOptions;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import io.vertx.ext.shell.*;
import io.vertx.ext.shell.command.CommandBuilder;
import io.vertx.ext.shell.command.CommandRegistry;
import io.vertx.ext.shell.command.CommandResolver;
import io.vertx.ext.shell.session.Session;
import io.vertx.ext.shell.system.Job;
import io.vertx.ext.shell.term.*;
import io.vertx.ext.web.Router;

public class DemoVertxShell {

    static Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        shellServiceViaTelnet();
        shellServiceViaSSH();
        shellServiceViaHTTP();

        customShellServiceViaTelnet();
        customShellServiceViaSSH();
        customShellServiceViaHTTP();

        customVertxShellCommand();
        customVertxShellCommandViaCLI();

        useTerminal();

        useShellServer();
        useShell();

        useSSHTerm();
        useTelnetTerm();
        useHTTPTerm();
    }

    public static void shellServiceViaTelnet() {
        vertx.deployVerticle(ShellVerticle::new,
                new DeploymentOptions().setConfig(
                        new JsonObject().put("telnetOptions",
                                new JsonObject().
                                        put("host", "localhost").
                                        put("port", 4000))
                ), ar -> {
                    if(ar.succeeded()){
                        System.out.println(ar.result());
                    }else{
                        System.out.println(ar.cause().toString());
                    }
                }
        );
    }

    public static void shellServiceViaSSH() {
        vertx.deployVerticle(ShellVerticle::new,
                new DeploymentOptions().setConfig(new JsonObject().
                        put("sshOptions", new JsonObject().
                                put("host", "localhost").
                                put("port", 5000).
                                put("keyPairOptions", new JsonObject().
                                        put("path", "src/resources/ssh.jks").
                                        put("password", "secret")).
                                put("authOptions", new JsonObject().
                                        put("provider", "shiro").
                                        put("config", new JsonObject().
                                                put("properties_path", "file:src/resources/auth.properties"))))
                ), ar -> {
                    if (ar.succeeded()) {
                        System.out.println(ar.result());
                    } else {
                        System.out.println(ar.cause().toString());
                    }
                }
        );
    }

    public static void shellServiceViaHTTP() {
        vertx.deployVerticle(ShellVerticle::new,
                new DeploymentOptions().setConfig(new JsonObject().
                        put("httpOptions", new JsonObject().
                                put("host", "localhost").
                                put("port", 8080).
                                put("ssl", true).
                                put("keyPairOptions", new JsonObject().
                                        put("path", "src/resources/keystore.jks").
                                        put("password", "secret")).
                                put("authOptions", new JsonObject().
                                        put("provider", "shiro").
                                        put("config", new JsonObject().
                                                put("properties_path", "file:src/resources/auth.properties"))))
                ), ar -> {
                    if (ar.succeeded()) {
                        System.out.println(ar.result());
                    } else {
                        System.out.println(ar.cause().toString());
                    }
                }
        );
    }

    public static void customShellServiceViaTelnet() {
        ShellService service = ShellService.create(vertx,
                new ShellServiceOptions().setTelnetOptions(
                        new TelnetTermOptions().
                                setHost("localhost").
                                setPort(4000)
                )
        );
        service.start();
    }

    public static void customShellServiceViaSSH() {
        ShellService service = ShellService.create(vertx,
                new ShellServiceOptions().setSSHOptions(
                        new SSHTermOptions().
                                setHost("localhost").
                                setPort(5000).
                                setKeyPairOptions(new JksOptions().
                                        setPath("src/resources/ssh.jks").
                                        setPassword("secret")
                                ).
                                setAuthOptions(new ShiroAuthOptions().
                                        setType(ShiroAuthRealmType.PROPERTIES).
                                        setConfig(new JsonObject().
                                                put("properties_path", "file:src/resources/auth.properties"))
                                )
                )
        );
        service.start();
    }

    public static void customShellServiceViaHTTP() {
        ShellService service = ShellService.create(vertx,
                new ShellServiceOptions().setHttpOptions(
                        new HttpTermOptions().
                                setHost("localhost").
                                setPort(8080)
                )
        );
        service.start();
    }

    public static void customVertxShellCommand(){
        CommandBuilder builder = CommandBuilder.command("my-command");
        builder.processHandler(process -> {
            process.write("Hello World");
            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void customVertxShellCommandViaCLI(){
        CLI cli = CLI.create("my-command").
                addArgument(new Argument().setArgName("my-arg")).
                addOption(new Option().setShortName("m").setLongName("my-option"));
        CommandBuilder command = CommandBuilder.command(cli);
        command.processHandler(process -> {
            CommandLine commandLine = process.commandLine();
            String argValue = commandLine.getArgumentValue(0);
            String optValue = commandLine.getOptionValue("my-option");
            process.write("The argument is " + argValue + " and the option is " + optValue);

            process.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(command.build(vertx));
    }

    public static void useTerminal(){
        CommandBuilder builder = CommandBuilder.command("tty");
        builder.processHandler(tty -> {
            tty.stdinHandler(data -> {
                System.out.println("Received " + data);
            });
            tty.write("Hello World");
            tty.write("Current terminal size: (" + tty.width() + ", " + tty.height() + ")");
            tty.resizehandler(v -> {
                System.out.println("terminal resized : " + tty.width() + " " + tty.height());
            });
            System.out.println("terminal type : " + tty.type());


            Session session =tty.session();
            if (session.get("my_key") == null) {
                session.put("my key", "my value");
            }


            Vertx loclaVertx = tty.vertx();
            loclaVertx.setTimer(1000, id -> {
                tty.end();
            });


            Vertx loclaVertx2 = tty.vertx();
            long periodicId = loclaVertx2.setPeriodic(1000, id -> {
                tty.write("tick\n");
            });
            tty.interruptHandler(v -> {
                loclaVertx2.cancelTimer(periodicId);
                tty.end();
            });


            tty.suspendHandler(v -> {
                System.out.println("Suspended");
            });
            tty.resumeHandler(v -> {
                System.out.println("Resumed");
            });


            tty.endHandler(v -> {
                System.out.println("Terminated");
            });

            tty.end();
        });
        CommandRegistry registry = CommandRegistry.getShared(vertx);
        registry.registerCommand(builder.build(vertx));
    }

    public static void useShellServer(){
        ShellServer server = ShellServer.create(vertx);

        Router router = Router.router(vertx);
        Router shellRouter = Router.router(vertx);
        router.mountSubRouter("/shell", shellRouter);

        TermServer httpTermServer = TermServer.createHttpTermServer(vertx, router);
        TermServer sshTermServer = TermServer.createSSHTermServer(vertx);

        server.registerTermServer(httpTermServer);
        server.registerTermServer(sshTermServer);

        server.registerCommandResolver(CommandResolver.baseCommands(vertx));
        server.listen();
    }

    public static void useShell(){
        ShellServer shellServer = ShellServer.create(vertx);
        Shell shell = shellServer.createShell();
        Job job = shell.createJob("my-command 1234");
        Pty pty = Pty.create();
        pty.stdoutHandler(data -> {
            System.out.println("Command wrote " + data);
        });
        job.setTty(pty.slave());
        job.statusUpdateHandler(status -> {
            System.out.println("Command terminated with status " + status);
        });
    }

    public static void useSSHTerm(){
        TermServer server = TermServer.createSSHTermServer(vertx, new SSHTermOptions().setPort(5000).setHost("localhost"));
        server.termHandler(term -> {
            term.stdinHandler(line -> {
                term.write(line);
            });
        });
        server.listen();
    }

    public static void useTelnetTerm(){
        TermServer server = TermServer.createTelnetTermServer(vertx, new TelnetTermOptions().setPort(5000).setHost("localhost"));
        server.termHandler(term -> {
            term.stdinHandler(line -> {
                term.write(line);
            });
        });
        server.listen();
    }

    public static void useHTTPTerm(){
        TermServer server = TermServer.createHttpTermServer(vertx, new HttpTermOptions().setPort(5000).setHost("localhost"));
        server.termHandler(term -> {
            term.stdinHandler(line -> {
                term.write(line);
            });
        });
        server.listen();
    }
}
