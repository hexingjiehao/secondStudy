package javaKnowledge.jdk;

import javaKnowledge.clone.CloneMain;
import javaKnowledge.clone.User;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by xiongjie on 2018/10/18.
 */
public class JdkTest {

    public static void main(String [] args){

        //测试自动关闭接口和try-catch-resource
//        try(JdkNewFeature lock = new JdkNewFeature()){
//            System.out.println("doing business...");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        try {
//            writeToFileZipFileContents("E://test.zip","E://xiongjie.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        hashMapNewMethod();

//        jdk8NewAnnotation();

    }


    /**
     * jdk1.7的try-with-resource测试
     * zip文件的流操作
     * @param zipFileName
     * @param outputFileName
     * @throws IOException
     */
    public static void writeToFileZipFileContents(String zipFileName,String outputFileName) throws IOException {

        Charset charset = Charset.forName("US-ASCII");
        Path outputFilePath = Paths.get(outputFileName);
        //打开zip文件，创建输出流
        try (
                ZipFile zf = new ZipFile(zipFileName);
                BufferedWriter writer = Files.newBufferedWriter(outputFilePath, charset)
        ) {//遍历文件写入txt
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                String newLine = System.getProperty("line.separator");
                String zipEntryName = ((ZipEntry) entries.nextElement()).getName() + newLine;
                writer.write(zipEntryName, 0, zipEntryName.length());
            }
        }
    }

    /**
     * jdk1.8-hashmap的新方法
     */
    public static void hashMapNewMethod(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1,"val1");
        map.put(2,"hello");
        for (int i = 0; i < 10; i++) {
            //如果有值，则不放入
            String value=map.putIfAbsent(i, "val" + i);
            System.out.println(value);
        }

        //如果存在，则计算key-value,并处理map
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        //全匹配删除,key匹配
//        map.remove(3, "val3");
        map.remove(3);
        System.out.println(map.get(3));

        //查找，没有就返回value
        map.getOrDefault("1","no found");

        //合并key对应的value和给定的value
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));

    }

    public static void jdk8NewAnnotation(){
        Hint hint = User.class.getAnnotation(Hint.class);
        System.out.println(hint);                   // null
        Hints hints1 = User.class.getAnnotation(Hints.class);
        System.out.println(hints1);
//        System.out.println(hints1.value().length);  // 2
        Hint[] hints2 = CloneMain.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);
    }



}
