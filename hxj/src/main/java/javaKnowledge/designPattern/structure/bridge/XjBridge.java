package javaKnowledge.designPattern.structure.bridge;

/**
 * Created by xiongjie on 2018/11/18.
 */
public class XjBridge extends Bridge {

    //重写抽象方法
    @Override
    public void showWeather(){
        getWeather().showWeather();
    }

    public static void main(String[] args){

        XjBridge xjBridge=new XjBridge();
        Weather weather=new Sun();
        xjBridge.setWeather(weather);
        xjBridge.showWeather();

        weather=new Rain();
        xjBridge.setWeather(weather);
        xjBridge.showWeather();

    }

}
