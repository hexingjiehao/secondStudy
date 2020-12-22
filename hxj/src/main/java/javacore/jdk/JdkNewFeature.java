package javacore.jdk;


import javacore.clone.User;

import java.beans.*;
import java.io.BufferedReader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xiongjie on 2018/10/18.
 */
public class JdkNewFeature implements AutoCloseable {

    @Retention(RetentionPolicy.SOURCE)
    public @interface SourceLevel {
    }
    @Retention(RetentionPolicy.CLASS)
    public @interface ClassLevel {
    }
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RuntimeLevel {
    }

    @Override
    public void close() throws Exception {
        System.out.println("实现自动关闭接口...GC回收后打印");
    }


    static int numStatic;
    int numClass;

    /**
     * 源码级别
     */
    @SourceLevel
    public void sourceLevel(){}

    /**
     * 类级别，运行时不可见
     */
    @ClassLevel
    public void classLevel(){};

    /**
     * 运行时级别，可见
     */
    @RuntimeLevel
    public void runtimeLevel(){};

    public static void main(String[] args){
        jdk5(1,2);
        reflectMethod();
        jdk7();
        jdk8();
        new JdkNewFeature().jdk8Dynamic();

        try {
            rethrow("First");
        } catch (FirstException | SecondException | ThirdException  e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * jdk1.5
     * 可变参数特性测试
     * 内省的操作对象属性
     */
    public static void jdk5(int... items) {

        BufferedReader b;

        //1.可变参数特性代码
        int num=0;
        int sum=0;
        for (int item:items) {
            num++;
            sum+=item;
        }
        System.out.println(String.format("num=[%s],sum=[%s]",num,sum));

        //2.开始内省特性的代码
        User user = new User();
        PropertyDescriptor pd = null;
        try {
            pd = new PropertyDescriptor("username", User.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        Method w = pd.getWriteMethod();//获取属性的setter方法
        try {
            w.invoke(user, "xiongjie");
            Method r = pd.getReadMethod();//获取属性的getter方法
            String res= (String) r.invoke(user, null);
            System.out.println(res);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        BeanInfo bi = null;
        try {
            //内省类
            bi = Introspector.getBeanInfo(User.class);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        //方法描述器
        MethodDescriptor[] methods=bi.getMethodDescriptors();
        for (MethodDescriptor md:methods){
            System.out.println(md.getMethod());
        }

        //属性描述器
        PropertyDescriptor[] pds = bi.getPropertyDescriptors();
        for(PropertyDescriptor p : pds){
            System.out.println(p.getDisplayName());
        }

    }

    /**
     * 反射的使用
     */
    public static void reflectMethod(){
        User user = new User();
        Field f = null;
        try {
            f = user.getClass().getDeclaredField("username");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        f.setAccessible(true);
        try {
            f.set(user, "hexingjie");//设置属性值
            String name = (String)f.get(user);//获取属性值

            System.out.println(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * jdk1.6
     * 新增注解的测试
     */
    public static void jdk6() {

    }

    /**
     * jdk1.7
     * 1.二进制字面量
     * 2.在数字字面量使用下划线
     */
    public static void jdk7() {
        //1.测试二进制字面量
        int a=0b11;
        int b=0B100;
        System.out.println(a+":"+b);

        //2.测试数字字面量使用下划线
        int x1 = 5_2;              // OK
        int x2 = 5_______2;        // OK
        int x3 = 0x5_2;            // OK
        int x4 = 0_52;             // OK--0前缀表示8进制
        int x5 = 05_2;            // OK
        System.out.println(x2+"="+x4+"="+x5);
    }

    /**
     * jdk1.8
     * 1.接口的默认方法
     * 2.lambda优化字符串比较器
     * 3.函数式接口,用lambda语言表示
     * 4.方法与构造函数的引用
     * 5.lambda访问局部变量
     * 7.java8提供的默认接口，我们可以使用lambda实现
     */
    public static void jdk8() {

         final int numLocal=1;

//        1.匿名实现类
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        double a=formula.calculate(100);     // 100.0
        double b=formula.sqrt(16);           // 4.0
        System.out.println(a+"="+b);


//        //2.lambda优化字符串比较器
//        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
//        Collections.sortFind(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });
//        System.out.println(names);
//
//        names = Arrays.asList("2", "3", "1", "0");
//        Collections.sortFind(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
//        System.out.println(names);
//
//        names = Arrays.asList("hello", "3", "world", "0");
//        Collections.sortFind(names, (String a, String b) -> b.compareTo(a));
//        System.out.println(names);
//
//        names = Arrays.asList("44", "beijing", "china", "5");
//        Collections.sortFind(names, (a, b) -> b.compareTo(a));
//        System.out.println(names);
//
////        3.函数式接口,用lambda语言表示
//        Formula formula=(a)-> (a * 100); //匿名实现类,实现了接口方法
//        double a=formula.calculate(100);
//        System.out.println(a);
//
////        4.方法与构造函数的引用
//        Formula formula=Integer::valueOf; //匿名实现类,实现了接口方法
//        double a=formula.calculate(100);
//        System.out.println(a);
//
//
////        使用 Person::new 来获取Person类构造函数的引用，Java编译器会自动根据UserFactory.create方法的签名来选择合适的构造函数。
//        UserFactory<User> factory=User::new;
//        User templates.user=factory.create();
//        System.out.println(templates.user.getUsername()+"="+templates.user.getPassword());
//
////        5.访问局部变量，lambda表达式
//        Formula formula=(a)->(a*num*20); //匿名实现类,实现了接口方法
//        double a=formula.calculate(100);
//        System.out.println(a);
//
////        7.基础的默认接口
//        Predicate<String> predicate = (s) -> s.length() > 0; //匿名实现类
//        System.out.println(predicate.test("123"));
//        System.out.println( predicate.negate().test("123") );
//        Predicate<Boolean> nonNull = Objects::nonNull;
//        System.out.println(nonNull.test(true));
//
//        Function<String,Integer> function=Integer::valueOf;
//        int a=function.apply("123");
//        System.out.println(a);
//
////        都是接口的匿名实现类，使用lambda表达式
//        Supplier<User> userSupplier=User::new;
//        User templates.user=userSupplier.get();
//        System.out.println(templates.user.getUsername()+"="+templates.user.getPassword());
//
//        Consumer<User> consumer=(templates.user)-> System.out.println(templates.user.getPassword());
//        consumer.accept(new User());
//
//        Comparator<User> comparable=(user1,user2)-> user1.getUsername().compareTo(user2.getUsername());
//        User user1=new User();
//        User user2=new User("ccc","sss");
//        int value=comparable.compare(user1,user2);
//        System.out.println(value);
//        //reversed()方法的原理是复制比较器，然后交换参数的位置
//        int value2=comparable.reversed().compare(user1,user2);
//        System.out.println(value2);

//        null的封装值,不能传递null
        Optional<String> optional = Optional.of("bam");
        System.out.println(optional.isPresent());           // true
        System.out.println(optional.get());                 // "bam"
        System.out.println(optional.orElse("fallback"));    // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"

        List<String> list =new ArrayList<>();
        list.add("ddd2");list.add("aaa2");list.add("bbb1");
        list.add("aaa1");list.add("bbb3");list.add("ccc");
        list.add("bbb2");list.add("ddd1");

        //集合得到流链条，然后这个流链条各种数据清洗
//        list.stream()
//            .map( String::toUpperCase )
//            .sorted((s1,s2)-> 0-s1.compareTo(s2))
//            .filter((s) -> s.startsWith("A"))
//            .forEach(System.out::println);// "aaa2", "aaa1"
//        System.out.println(list);
//
//        //多种匹配方法
//        boolean a1=list.stream()
//            .anyMatch((s)->s.startsWith("a"));
//        System.out.println(a1);
//
//        boolean a2=list.stream()
//                .allMatch((s)->s.startsWith("a"));
//        System.out.println(a2);
//
//        boolean a3=list.stream()
//                .noneMatch((s)->s.startsWith("z"));
//        System.out.println(a3);
//
//        System.out.print( list.stream()
//                              .filter((s) -> s.startsWith("b"))
//                              .count() );

//        Optional<String> optional=list.stream()
//                                        .sorted()
//                                        .reduce( (c1,c2)-> c1+"|"+c2 );

        //用方法作为参数
        optional.ifPresent(System.out::println);


    }

    /**
     * 6.访问对象字段和静态变量
     */
    public void jdk8Dynamic(){
        //6.访问对象字段和静态变量
        Formula formula1=(a)->{
            numClass=2;
            System.out.println(numClass);
            return a;
        }; //匿名实现类,实现了接口方法
        double a1=formula1.calculate(100);
        System.out.println(a1);

        Formula formula2=(a)->{
            numStatic=3;
            System.out.println(numStatic);
            return a;
        }; //匿名实现类,实现了接口方法
        double a2=formula2.calculate(100);
        System.out.println(a2);

    }

    /**
     * jdk1.7
     * 测试重新抛出异常的类型检查，人为缩小范围
     *
     * @param s
     * @throws FirstException
     * @throws SecondException
     * @throws ThirdException
     */
    public static void rethrow(String s) throws FirstException, SecondException, ThirdException {

        try {
            if (s.equals("First")) {
                throw new FirstException("First");
            }else if (s.equals("Second")) {
                throw new SecondException("Second");
            }else {
                throw new ThirdException("Third");
            }
        } catch (Exception e) {
            //下面的赋值没有启用重新抛出异常的类型检查功能，这是Java 7的新特性
            // e=new ThirdException();
            throw e;
        }
    }

    static class FirstException extends Exception {

        public FirstException(String msg) {
            super(msg);
        }
    }

    static class SecondException extends Exception {

        public SecondException(String msg) {
            super(msg);
        }
    }

    static class ThirdException extends Exception {

        public ThirdException(String msg) {
            super(msg);
        }
    }


}

