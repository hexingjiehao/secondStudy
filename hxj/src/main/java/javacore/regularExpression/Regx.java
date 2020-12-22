package javacore.regularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regx {

    public static void main(String[] args) {
        regularExpress("[\u4e00-\u9fa5]", "熊");
        regularExpress("[^\\x00-\\xff]", "熊");
        regularExpress("\\s", " ");
        regularExpress("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}", "2711992339@qq.com");
        regularExpress("^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+", "http://www.baidu.com");
        regularExpress("0?(13|14|15|17|18|19)[0-9]{9}", "18351807162");
        regularExpress("[0-9-()（）]{7,18}", "0830-8283515");
        regularExpress("[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*", "1.1");
        regularExpress("-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)", "-1.1");
        regularExpress("-?[1-9]\\d*|0", "5");
        regularExpress("[1-9]\\d*", "11");
        regularExpress("-[1-9]\\d*", "-11");
        regularExpress("[1-9]([0-9]{5,11})", "2711992339");
        regularExpress("\\d{6}", "646128");
        regularExpress("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)", "127.0.0.1");
        regularExpress("\\d{17}[\\d|x]|\\d{15}", "510531199312211300");
        regularExpress("\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}", "1994-12-22");
        regularExpress("[A-Za-z0-9_\\-\u4e00-\u9fa5]+", "熊杰");
    }

    public static void regularExpress(String pattern, String str) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
