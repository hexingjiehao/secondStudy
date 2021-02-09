package hardware.java监视笔记本电脑的电池或电源;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: hardware.java监视笔记本电脑的电池或电源
 * @className MonitorLaptopBattery
 * @description: JAVA程序判断电脑是否连接到电源
 * @author: xj
 * @create: 2021-02-09 16:04:49
 **/
public class MonitorLaptopBattery {

    public static void main(String[] args) {
        monitorBatterByVB();
        monitorBatterByJNA();
    }

    /*
    * 使用VB的cmd命令访问电池，测试失败了
    * */
    public static void monitorBatterByVB(){
        try {
            Process proc = Runtime.getRuntime().exec("cmd.exe /c battstat.bat");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                if (s.contains("mains power")) {
                    System.out.println("电脑连接电源失败");
                } else if (s.contains("Discharging")) {
                    System.out.println("电脑连接电源OK");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /*
     * 使用JNA访问笔记本电池。测试OK
     * */
    public static void monitorBatterByJNA(){
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);

        System.out.println(batteryStatus.isPlugged());
    }
}
