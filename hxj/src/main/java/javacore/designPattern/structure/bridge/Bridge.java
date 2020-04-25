package core.designPattern.structure.bridge;

/**
 * Created by xiongjie on 2018/11/18.
 *
 * 桥的内部结构就是接口的get/set和抽象方法实现
 */
public abstract class Bridge {

    private Weather weather;

    public abstract void showWeather();

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
