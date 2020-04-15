package algorithm.onlyId;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 参考资料：https://cloud.tencent.com/developer/ask/173559
 * 使用UUID生成唯一的Long
 */
public class UUIDResult {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());

        //这是生成19位的唯一数字
        long res=UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        System.out.println(res);

        Map map=new HashMap();
        for (int i=0;i<1000000;i++){
            long id=UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            if(map.get(id)!=null){
                System.out.println("产生重复数据："+id+"此时map的size:"+map.size());
                break;
            }
            map.put(id,id);
        }

        //这是生成15位的唯一数字
        Long value=111111111111111L;
        long res2=UUID.randomUUID().getMostSignificantBits() & value;
        System.out.println(res2);

        Map map2=new HashMap();
        for (int i=0;i<10000000;i++){
            long id=UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            if(map2.get(id)!=null){
                System.out.println("产生重复数据："+id+"此时map的size:"+map2.size());
                break;
            }
            map2.put(id,id);
        }
    }
}
