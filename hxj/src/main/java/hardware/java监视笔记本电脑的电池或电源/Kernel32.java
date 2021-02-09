package hardware.java监视笔记本电脑的电池或电源;

import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: hardware.java监视笔记本电脑的电池或电源
 * @className Kernel32
 * @description: jna通过代理的方法，调用Kernel32.dll中的GetSystemPowerStatus方法，传入参数ACLineStatus
 * @author: xj
 * @create: 2021-02-09 16:27:43
 **/
public interface Kernel32 extends StdCallLibrary {
    public Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("Kernel32",
            Kernel32.class);

    public class SYSTEM_POWER_STATUS extends Structure {
        public byte ACLineStatus;

        @Override
        protected List<String> getFieldOrder() {
            ArrayList<String> fields = new ArrayList<String>();
            fields.add("ACLineStatus");

            return fields;
        }

        public boolean isPlugged() {
            return ACLineStatus == 1;
        }
    }

    public int GetSystemPowerStatus(SYSTEM_POWER_STATUS result);
}
