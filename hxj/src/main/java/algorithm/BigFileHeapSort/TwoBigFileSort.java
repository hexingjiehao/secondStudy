package algorithm.BigFileHeapSort;

import java.io.File;

public class TwoBigFileSort {

    public static void main(String[] args) {
        File file1=FileUtil.generateBigFile("gBig1.csv");
        File file2=FileUtil.generateBigFile("gBig2.csv");

        File fileG1=BigFileHeapSort.sortNodeByFile(file1,"g1.csv");
        File fileG2=BigFileHeapSort.sortNodeByFile(file2,"g2.csv");

        File res=FileUtil.SubtractByDbAndCos(fileG1,fileG2);
        System.out.println("文件为空？"+ (res==null));

        File file=new File("cosSub.csv");
        file.delete();
        file1.delete();
        file2.delete();
        fileG1.delete();
        fileG2.delete();
    }
}
