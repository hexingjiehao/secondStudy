package algorithm;

import java.util.Arrays;

/**
 * Created by xiongjie on 2018/11/17.
 * 递归排序和查找算法
 * 分治思想
 */
public class RecursionSorting {

    public static void main(String[] args){
        int[] arr=new int[]{1,4,6,3,4,9,3,5,0,2,8};
//        MergeSort(arr,11);
        QSort(arr,0,10);
        System.out.println(Arrays.toString(arr));
    }


    //这是双链表合并
    static void  Merge(int[] a,int low,int mid,int high) {
        int i=low,j=mid+1,p=0;
        int[] r=new int[high-low+1];
        while(i<=mid && j<=high) {
            r[p++]=(a[i]<=a[j])?a[i++]:a[j++];
        }
        while(i<=mid) {
            r[p++] = a[i++];
        }
        while(j<=high){
            r[p++]=a[j++];
        }
        //将排好序的数组重新赋值回去
        for(p=0,i=low;i<=high;p++,i++) {
            a[i] = r[p];
        }
    }

    //将数据按照每组多少个的长度分解，分解后，每个结果合并
    //假设length=2,每次合并[0,3],[4,6],[7,9]...
    static void MergePass(int[] a,int n,int length) {
        int i=0;
        for(;i+2*length<n-1;i+=2*length) {
            int left=i;
            int mid=i+length-1;
            int right=i+2*length-1;
            System.out.println("大：left="+left+"mid="+mid+"right="+right);
            Merge(a,left,mid,right);
        }
        if(i+length<=n-1) {
            int left=i;
            int mid=i+length-1;
            int right=n - 1;
            System.out.println("小：left="+left+"mid="+mid+"right="+right);
            Merge(a, i, i + length - 1, n - 1);
        }
        System.out.println("拆分间隔为"+length+"的结果："+Arrays.toString(a));
    }


    //归并排序--每次的合并间距翻倍
    static void MergeSort(int[] a,int n) {
        for(int length=1;length<n;length*=2) {
            System.out.println("拆分间隔="+length);
            MergePass(a, n, length);
        }
    }


    //快速排序的递归实现和循环实现
    static int Partition(int arr[],int low,int high){
        int temp =arr[low];
        while(low<high){    //low和high从待排序列两端向中间移动
            while(low<high&&arr[high]>=temp){
                --high;
            }
            arr[low] = arr[high];    //将比temp小的移到低端
            while(low<high&&arr[low]<=temp){
                ++low;
            }
            arr[high] = arr[low];    //将比temp大的移到高端
        }
        arr[low] = temp;
        return low;
    }

    static void QSort(int arr[],int s,int t){
        int pirvotloc;
        if(s<t){
            pirvotloc = Partition(arr,s,t); //进行划分并返回基数位置
            QSort(arr,s,pirvotloc-1);       //对基数前的子序列进行递归排序
            QSort(arr,pirvotloc+1,t);      //对基数后的子序列进行递归排序
        }
    }

}
