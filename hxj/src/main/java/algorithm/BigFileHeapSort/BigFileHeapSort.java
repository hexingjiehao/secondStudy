package algorithm.BigFileHeapSort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class BigFileHeapSort {

    public static void main(String[] args) {
        File file=new File("bigFile.csv");
        sortNodeByFile(file,null);
    }

    public static File sortNodeByFile(File file,String name){
        File totalFile=null;

        //获取集合的个数
        int dataCount=FileUtil.countFileLines(file);
        int markCount=0;

        List<Node> list=new ArrayList<>();  //模拟堆
        Map<String,Integer> map=new HashMap<>();    //用于标记堆中的节点的位置，值-堆中下标

        int heapSize=1000;     //堆的总容量
        int headIndex=0;    //当前遍历的下标
        String headMax="0";     //当前遍历的最大值
        String lastMax="0";     //上一轮遍历的最大值

        while(markCount<dataCount) {

            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
                String value;
                while ((value=br.readLine()) != null) {

                    if(value.compareTo(lastMax) <= 0){
                        continue;
                    }

                    if (headIndex < heapSize) {

                        Integer index = map.get(value);
                        if (index == null) {
                            list.add(new Node(value));
                            map.put(value, headIndex);
                            headIndex++;

                            if (headIndex == heapSize) {
                                //堆满，创建大根堆
                                for (int i = heapSize / 2 - 1; i >= 0; i--) {
                                    adjustHeapNode(list, i, heapSize, map);
                                }

                                headIndex++;
                                headMax = list.get(0).getValue();
                            }

                        } else {
                            Node node = list.get(index);
                            node.addOne();
                        }

                    } else if (headIndex >= heapSize) {
                        //调整堆
                        Integer index = map.get(value);
                        if (index == null) {
                            if (headMax.compareTo(value) < 0) {
                                headIndex++;
                            } else {
                                //替换掉最大值
                                map.put(value, 0);
                                list.set(0, new Node(value));
                                adjustHeapNode(list, 0, heapSize, map);
                                headMax = list.get(0).getValue();
                            }

                        } else {
                            Node node = list.get(index);
                            node.addOne();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //当堆的大小不到指定值时
            if(list.size()<heapSize){
                //创建大根堆
                for (int i = list.size()/ 2 - 1; i >= 0; i--) {
                    adjustHeapNode(list, i, list.size(), map);
                }
                //获取最值
                headMax = list.get(0).getValue();
                lastMax = headMax;

                //堆排序
                for (int j = list.size() - 1; j > 0; j--) {
                    swapNode(list, 0, j, map);//将堆顶元素与末尾元素进行交换
                    adjustHeapNode(list, 0, j, map);//重新对堆进行调整
                }
                System.out.println(list);

            }else {

                //获取最值
                headMax = list.get(0).getValue();
                lastMax = headMax;

                //堆排序
                for (int j = heapSize - 1; j > 0; j--) {
                    swapNode(list, 0, j, map);//将堆顶元素与末尾元素进行交换
                    adjustHeapNode(list, 0, j, map);//重新对堆进行调整
                }
                System.out.println(list);

            }

            //统计已经处理的个数：并写入文件
            for (Node node : list) {
                int count=node.getTimes();
                markCount += node.getTimes();

                String value=node.getValue();
                List<String> res=new ArrayList<>();
                for(int i=0;i<count;i++){
                    res.add(value);
                }

                if(name==null){
                    totalFile= FileUtil.createCsvFile(res,"dbHeapSort.csv");
                }else{
                    totalFile= FileUtil.createCsvFile(res,name);
                }
            }

            //数据清理
            list.clear();
            headIndex=0;
            map.clear();

            System.out.println(markCount);

        }
        System.out.println("集合数据全部排序完成");
        return totalFile;
    }

    /**
     * 每次将一个非叶子的节点最后后放在某个位置，隐含一种递归的想法
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeapNode(List<Node> arr,int i,int length,Map<String,Integer> map){
        Node temp = arr.get(i);//先取出当前元素i

        for(int k=i*2+1; k<length; k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始

            if(k+1<length && arr.get(k).getValue().compareTo(arr.get(k+1).getValue())<0  ){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }

            if(arr.get(k).getValue().compareTo(temp.getValue()) > 0){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                map.put(arr.get(k).getValue(),i);
                arr.set(i,arr.get(k));
                i = k;

            }else{
                break;
            }
        }
        map.put(temp.getValue(),i);
        arr.set(i,temp);//将temp值放到最终的位置
    }

    public static void swapNode(List<Node> arr,int a ,int b,Map<String,Integer> map){
        Node tmp=arr.get(a);
        map.put(tmp.getValue(),b);
        map.put(arr.get(b).getValue(),a);

        arr.set(a,arr.get(b));
        arr.set(b,tmp);
    }
}
