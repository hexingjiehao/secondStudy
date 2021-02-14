package tool.随机词语系统;

import java.awt.*;
import java.io.*;

/**
 * @version 1.0
 * @program: hxj
 * @packageName: tool.随机词语系统
 * @className RandomWord
 * @description: 随机的词语系统
 * @author: xj
 * @create: 2021-02-09 17:12:17
 **/
public class RandomWord {

    public static void main(String[] args) {
        //0.获取本地字库
        getLocalFontDictionary();
        //1.读取3753常用汉字txt文件。将其转化为字符串数组
        char[] chars = readTxtToArray();
        //2.将字符串数字逐行写入一个txt文件
        writeArrayToTxt(chars);
        //3.将字符串数组存储为数据库sql
        writeArrayToSql(chars);
        //4.将字符串数组随机生成2字词语
        randomGenerateWord(chars);
    }

    private static void getLocalFontDictionary() {
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String evnfonts[] = gEnv.getAvailableFontFamilyNames();
        System.out.println("共有 " + evnfonts.length);
        for (int i = 0; i < evnfonts.length; i++) {
            System.out.println(evnfonts[i]);
            Font f = Font.decode(evnfonts[i]);
            System.out.println();
        }
    }

    private static char[] readTxtToArray() {
        File sourceFile = new File("hxj/src/main/java/tool/随机词语系统/3753常用汉字.txt");
        if (sourceFile.exists()) {
            System.out.println(sourceFile.getName() + "存在");
        } else {
            System.out.println(sourceFile.getName() + "不存在");
        }

        String result = "";
        BufferedReader br = null;
        FileInputStream fis = null;
        String line = null;
        try {
            fis = new FileInputStream(sourceFile);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                result = result + line;
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
        System.out.println(result);
        char[] chars = result.toCharArray();
        System.out.println(chars);
        return chars;
    }

    private static void writeArrayToTxt(char[] chars) {
        File destFile = new File("hxj/src/main/java/tool/随机词语系统/3753常用汉字(规整后).txt");
        if (!destFile.exists()) {
            try {
                destFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destFile);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (char c : chars) {
                bw.write(c);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeArrayToSql(char[] chars) {
        File destFile = new File("hxj/src/main/java/tool/随机词语系统/frequent_fonts_init.sql");
        if (!destFile.exists()) {
            try {
                destFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String module = "insert into frequent_fonts (name) values ('#');";

        BufferedWriter bw = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(destFile);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (char c : chars) {
                String line = module.replace('#', c);
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void randomGenerateWord(char[] chars) {
        int length = chars.length;
        //暂时随机生成100个2字词语
        for (int i = 0; i < 100; i++) {
            int first = (int) (Math.random() * length);
            int second = (int) (Math.random() * length);

            String result=chars[first]+""+chars[second];
            System.out.println(result);
        }
    }
}
