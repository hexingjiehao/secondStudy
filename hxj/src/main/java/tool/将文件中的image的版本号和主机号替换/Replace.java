package tool.将文件中的image的版本号和主机号替换;

import java.io.*;
import java.util.ArrayList;

public class Replace {
    static ArrayList<File> fileList = new ArrayList<File>();

    public static void main(String[] args) {
        String path="/Users/admin/Downloads/nextcloud/";
        String oldVersion="0.23.0-SNAPSHOT-11-jre";
        String newVersion="0.24.0-SNAPSHOT-11-jre";
        String oldHost="192.168.1.130";
        String newHost="192.168.1.14";
        getFiles(path);
        for (File file:fileList) {
            System.out.println(file.getName());
            replacTextContent(file,oldVersion,newVersion,oldHost,newHost);
        }
    }

    //获取目录下的所有部署文件
    public static void getFiles(String path){
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath());
                } else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    if(fileIndex.getName().contains("deploy") || fileIndex.getName().contains("config.yml")){
                        fileList.add(fileIndex);
                    }
                }
            }
        }
    }

    //替换文本内容
    public static void replacTextContent(File file,String oldVersion,String newVersion,String oldHost,String newHost){
        try {
            FileReader in = new FileReader(file);
            BufferedReader bufIn = new BufferedReader(in);
            // 内存流, 作为临时流
            CharArrayWriter tempStream = new CharArrayWriter();
            // 替换
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                // 替换每行中, 符合条件的字符串
                line = line.replaceAll(oldVersion, newVersion);
                line = line.replaceAll(oldHost, newHost);
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            }
            // 关闭 输入流
            bufIn.close();
            // 将内存中的流 写入 文件
            FileWriter out = new FileWriter(file);
            tempStream.writeTo(out);
            out.close();
            System.out.println(file.getPath()+"版本修改完成");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
