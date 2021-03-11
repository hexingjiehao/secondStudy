//package algorithm.BigFileHeapSort;
//
//import org.joda.time.DateTime;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//public class FileUtil {
//
//    //统计文件的行数
//    public static int countFileLines(File file){
//        int res=0;
//
//        BufferedReader br = null;
//        try {
//            br = new BufferedReader(new FileReader(file));
//            while ((br.readLine()) != null) {
//                res++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return res;
//    }
//
//    //生成大文件
//    public static File generateBigFile(String name){
//        File file=null;
//        String fileName="bigFile.csv";
//        if(name!=null) {
//            fileName = name;
//        }
//
//        List<String> list=new ArrayList<>();
////        int number = 16777216;  //1G文件
//        int number = 10000;  //1万行文件
//        for (int i = 0; i < number; i++) {
//            String line= UUID.randomUUID().toString() + new DateTime();
//            list.add(line);
//            System.out.println(i);
//
//            if(i%1000000==0){
//                file=createCsvFile(list,fileName);
//                list.clear();
//                System.out.println("追加文件内容：当前行数"+i);
//            }
//        }
//        if(list.size()>0){
//            file=createCsvFile(list,fileName);
//            list.clear();
//        }
//        return file;
//    }
//
//    //创建文件
//    public static File createCsvFile(List<String> list, String fileName) {
//        File file = null;
//        BufferedWriter bw = null;
//
//        try {
//            file = new File(fileName); // CSV数据文件
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            bw = new BufferedWriter(new FileWriter(file, true)); // 附加
//            for (String line : list) {
//                bw.write(line);
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bw != null) {
//                try {
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return file;
//    }
//
//
//    public static File SubtractByDbAndCos(File dbFileList, File cosFileList) {
//        File totalFile=null;
//        BufferedReader brDb = null;
//        BufferedReader brCos = null;
//
//        int dbCount=0;
//        int cosCount=0;
//
//        String dbLine="NAN";
//        String cosLine="NAN";
//        int flag=0;     //-1表示db的数据小，进行移动；1表示cos的数据小，进行移动。0表示双方都移动
//        List<String> list=new ArrayList<>();
//
//        try {
//            brDb = new BufferedReader(new FileReader(dbFileList));
//            brCos = new BufferedReader(new FileReader(cosFileList));
//            while ((dbLine=brDb.readLine()) != null) {
//
//                dbCount++;
//
//                if(flag==-1){
//                    if( dbLine.compareTo(cosLine)<0 ){
//                        flag=-1;
//                        continue;
//
//                    }else if( dbLine.compareTo(cosLine)>0 ){
//                        flag=1;
//
//                        //统计结果，写入文件
//                        list.add(cosLine);
//                        if(list.size()>=1000000){
//                            totalFile=createCsvFile(list,"cosSub.csv");
//                            list.clear();
//                        }
//
//                    }else{
//                        System.out.println(dbLine);
//                        flag=0;
//                        continue;
//                    }
//                }
//
//                if(dbLine==null || dbLine.equals("")){
//                    break;
//                }
//
//                while ( (cosLine=brCos.readLine()) != null ){
//
//                    cosCount++;
//
//                    if( dbLine.compareTo(cosLine)<0 ){
//                        flag=-1;
//                        break;
//
//                    }else if( dbLine.compareTo(cosLine)>0 ){
//                        flag=1;
//
//                        //统计结果，写入文件
//                        list.add(cosLine);
//                        if(list.size()>=1000000){
//                            totalFile=createCsvFile(list,"cosSub.csv");
//                            list.clear();
//                        }
//
//                    }else{
//                        System.out.println(dbLine);
//                        flag=0;
//                        break;
//                    }
//                }
//
//                if(cosLine==null || cosLine.equals("")){
//                    break;
//                }
//
//            }
//
//            if(cosLine!=null){
//                list.add(cosLine);
//            }
//
//            while ( (cosLine=brCos.readLine()) != null ){
//                list.add(cosLine);
//            }
//
//            if(list.size()>0){
//                totalFile=createCsvFile(list,"cosSub.csv");
//                list.clear();
//            }
//
//
//
//            System.out.println("db="+dbCount);
//            System.out.println("cos="+cosCount);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (brDb != null) {
//                    brDb.close();
//                }
//                if (brCos != null) {
//                    brCos.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//        return totalFile;
//    }
//
//}
