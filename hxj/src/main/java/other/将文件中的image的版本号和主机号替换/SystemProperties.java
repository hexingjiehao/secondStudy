package other.将文件中的image的版本号和主机号替换;

public class SystemProperties {

    /**
     * 参考：https://www.cnblogs.com/acm-bingzi/p/6673823.html
     *
     * 获取和设置系统变量
     * 系统变量不等于环境变量
     *
     * 设置java的系统属性：java -D配置系统属性   【注意-D和属性名要挨在一起才生效】
     *
     * 公司项目中设置的qjnext.under-test影响验证码的取值等操作
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("获取环境变量：");
        System.out.println(System.getenv("CLASSPATH"));
        System.out.println("获取系统变量{启动是添加参数-Dqjnext.under-test=?}：");
        System.out.println(System.getProperty("qjnext.under-test"));
    }
}
