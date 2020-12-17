package tool.idea;

import java.util.UUID;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: tool.idea
 * @className UUIDUtil
 * @description: TODO
 * @author: xj
 * @create: 2020-12-07 21:05:32
 **/
public class UUIDUtil {

    /**
     * @Description 生成32位UUID
     * @status done
     * @methodName main
     * @param
     * @return void
     * @Author xj
     * @Date 2020/12/7 21:05
     **/
    public static void main(String[] args) {
        int total=2;
        for(int i=0;i<total;i++){
            String uuid = UUID.randomUUID().toString();
            uuid = uuid.replace("-", "");
            System.out.println(uuid);
        }

    }

}
