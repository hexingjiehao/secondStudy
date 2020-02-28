package other.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class JMXTest {

    public static void main(String[] args) throws Exception{
        standardMBean();
//        dynamicMBean();
    }


    public static void standardMBean() throws Exception{
        MBeanServer mBeanServer= ManagementFactory.getPlatformMBeanServer();

        ObjectName name=new ObjectName("agent:name=test");
        SimpleMBeanImpl simpleMBeanImpl=new SimpleMBeanImpl();

        //标准MBean有点问题
        mBeanServer.registerMBean(simpleMBeanImpl,name);
        System.out.println("标准MBean注册成功");
    }

    public static void dynamicMBean() throws Exception{
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("agent:name=test");
        SimpleDynamicMBean simpleDynamicMBean = new SimpleDynamicMBean();
        mBeanServer.registerMBean(simpleDynamicMBean, name);
        System.out.println("动态MBean注册成功");
    }

}
