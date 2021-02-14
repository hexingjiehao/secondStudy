package tool.随机词语系统;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: tool.随机词语系统
 * @className RandomGamePersonName
 * @description: 随机的游戏人物命名系统
 * @author: xj
 * @create: 2021-02-09 17:06:31
 **/
public class RandomGamePersonName {

    public static void main(String[] args) {
        //1.读取文字数组
        String firstNameFile="hxj/src/main/java/tool/随机词语系统/百家姓.txt";
        String secondNameFile="hxj/src/main/java/tool/随机词语系统/3753常用汉字(规整后).txt";
        List<String> firstNameArray = readTxtToArray(firstNameFile);
        List<String> secondNameArray = readTxtToArray(secondNameFile);
        //2.将字符串数组随机生成2~4字名字
        randomGenerateWord(firstNameArray,secondNameArray);
    }

    private static List<String> readTxtToArray(String filePath) {
        File sourceFile = new File(filePath);
        if (sourceFile.exists()) {
            System.out.println(sourceFile.getName() + "存在");
        } else {
            System.out.println(sourceFile.getName() + "不存在");
        }

        List<String> result=new ArrayList<>();
        BufferedReader br = null;
        FileInputStream fis = null;
        String line = null;
        try {
            fis = new FileInputStream(sourceFile);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static void randomGenerateWord(List<String> firstNameArray,List<String> secondNameArray) {
        int firstNameArrayLength = firstNameArray.size();
        int secondNameArrayLength = secondNameArray.size();

        //暂时随机生成100个2~4字姓名
        for (int i = 0; i < 100; i++) {

            int nameLength=(int) (Math.random() * 2)+1;
            if(nameLength==1){
                int firstOne = (int) (Math.random() * firstNameArrayLength);
                int secondOne = (int) (Math.random() * secondNameArrayLength);
                String result=firstNameArray.get(firstOne)+""+secondNameArray.get(secondOne);
                System.out.println(result);
            } else if(nameLength==2){
                int firstOne = (int) (Math.random() * firstNameArrayLength);
                int secondOne = (int) (Math.random() * secondNameArrayLength);
                int secondTwo = (int) (Math.random() * secondNameArrayLength);
                String result=firstNameArray.get(firstOne)+""+secondNameArray.get(secondOne)+""+secondNameArray.get(secondTwo);
                System.out.println(result);
            }

        }
    }
}
