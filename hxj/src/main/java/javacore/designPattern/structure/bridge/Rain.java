package javacore.designPattern.structure.bridge;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class Rain implements Weather {
    @Override
    public void showWeather() {
        System.out.println("桥接模式：天气雨");
    }
}
