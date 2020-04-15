package algorithm;

/**
 * Created by xiongjie on 2018/11/17.
 */
public class StrReplcaeEfficiency {

    public static void main(String[] args){
        String str="0abc123def456ghij789k";
        long start=System.currentTimeMillis();
        String res1=oneByoneCharDelete(str);
        System.out.println("逐个字符串处理的结果："+res1+"花费时间"+(System.currentTimeMillis()-start) );

        long start2=System.currentTimeMillis();
        String res2=forReplaceNumber(str);
        System.out.println("循环替换的结果："+res2+"花费时间"+(System.currentTimeMillis()-start2) );
    }

    private static String oneByoneCharDelete(String str) {
        char[] arr=str.toCharArray();
        int len=str.length();

        char[] res=new char[len];
        int index=0;
        for(int i=0;i<len;i++){
            if(arr[i]>='0' && arr[i]<='9'){
                continue;
            }
            res[index++]=arr[i];
        }
        str=String.valueOf(res);
        str=str.trim();  //添加trim去除多余空格后,时间从1ms变成1ms或者2ms
//        str=str.substring(0,index);  //添加substring去除多余空格后,时间从7-11ms变成7-18ms(不稳定)
        return str;
    }

    //这种效率不高--有时候想法不一定有实际用途
    private static String forReplaceNumber(String str) {
        for(int i=0;i<10;i++){
            str=str.replace(i+"","");
        }
        return str;
    }

}
