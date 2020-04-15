package core.jdk;

/**
 * Created by xiongjie on 2018/10/18.
 */
@FunctionalInterface
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}
