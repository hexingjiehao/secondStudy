package tool.log4j学习;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.Test;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: tool.log4j学习
 * @className LogTest
 * @description: 学习log4j的底层原理，如何在控制台打印日志
 * @author: xj
 * @create: 2021-02-03 15:04:19
 **/
public class LogTest {

    /*这个是最重要的，包来自org.apache.log4j.Logger,而不是其他的*/
    private Logger logger;

    @Before
    public void initialize(){
        logger= Logger.getLogger("logger");
        logger.removeAllAppenders();
        Logger.getRootLogger().removeAllAppenders();
    }

    @Test
    public void basicLogger(){
        BasicConfigurator.configure();
        logger.info("basicLogger");
    }

    @Test
    public void addAppenderWithStream(){
        logger.addAppender(new ConsoleAppender(
                new PatternLayout("%p %t %m%n"),
                ConsoleAppender.SYSTEM_OUT
        ));
        logger.info("addAppenderWithStream");
    }

    @Test
    public void addAppenderWithoutStream(){
        logger.addAppender(new ConsoleAppender(
                new PatternLayout("%p %t %m%n")
        ));
        logger.info("addAppenderWithStream");
    }
}
