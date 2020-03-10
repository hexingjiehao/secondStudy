package core.clone;

/**
 * Created by xiongjie on 2018/10/17.
 */
//@Hint("hint1")
//@Hint("hint2")
public class CloneMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        dealCloneDefine();
        dealDeepClone();
        dealDeepCloneBySerilizable();
    }

    /**
     * 1.克隆的定义测试，只需要实现cloneable的接口
     * 克隆出来的对象时新的对象，不是原来的地址
     * @throws CloneNotSupportedException
     */
    public static void dealCloneDefine() throws CloneNotSupportedException {
        User userOne, userTwo, userThree;
        userOne = new User("username", "password");
        userTwo = userOne;
        userThree = (User) userOne.clone();

        System.out.println(userTwo==userOne);            //true--地址比较
        System.out.println(userTwo.equals(userOne));    //true--内容比较

        System.out.println(userThree==userOne);            //false--地址比较
        System.out.println(userThree.equals(userOne));    //true--内容比较
    }

    /**
     * 2.浅克隆和深克隆的区别
     * 浅克隆对于类内部的属性对象，只克隆地址。但是外部的对象已经是新的对象了
     * 深克隆将new一个新的对象，但是内容相同
     * @throws CloneNotSupportedException
     */
    public static void dealDeepClone() throws CloneNotSupportedException {
        Company companyOne, companyTwo, companyThree;
        companyOne = new Company(new User("username", "password"), "上海市");
        companyTwo = companyOne;
        companyThree = (Company) companyOne.clone();

        System.out.println(companyTwo==companyOne);                //true
        System.out.println(companyTwo.equals(companyOne));        //true

        System.out.println(companyThree==companyOne);            //false
        System.out.println(companyThree.equals(companyOne));    //true

        System.out.println(companyThree.getUser()==companyOne.getUser());            //false
        System.out.println(companyThree.getUser().equals(companyOne.getUser()));    //true
    }


    /**
     * 3.工具类可以不必是抽象类，主要是静态方法而已
     * 使用抽象类的静态方法进行深层克隆，对象需要实现serilizable接口
     * @throws CloneNotSupportedException
     */
    public static void dealDeepCloneBySerilizable() {
        Company companyOne, companyTwo, companyThree;
        companyOne = new Company(new User("username", "password"), "上海市");
        companyTwo = companyOne;
        companyThree = BeanUtils.cloneTo (companyOne);

        System.out.println(companyTwo==companyOne);                //true
        System.out.println(companyTwo.equals(companyOne));        //true

        System.out.println(companyThree==companyOne);            //false
        System.out.println(companyThree.equals(companyOne));    //true

        System.out.println(companyThree.getUser()==companyOne.getUser());            //false
        System.out.println(companyThree.getUser().equals(companyOne.getUser()));    //true
    }


}
