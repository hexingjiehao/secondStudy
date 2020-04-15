package algorithm;

/**
 * Created by xiongjie on 2020/4/9.
 */
public class QuickSort {

    /**
     * 快速排序的逻辑：找哨兵，然后递归左右两边
     * @param args
     */
    public static void main(String[] args) {
        int arr[] = new int[]{7,1,2,9,3,8,4,10,5,6};
        int len = arr.length-1;
        arr=qsort(arr,0,len);
        for (int i:arr) {
            System.out.print(i+"\t");
        }
    }

    public static int[] qsort(int arr[],int start,int end) {
        int pivot = arr[start]; //哨兵
        int i = start;
        int j = end;
        while (i<j) {
            while ((i<j)&&(arr[j]>pivot)) {
                j--;
            }
            while ((i<j)&&(arr[i]<pivot)) {
                i++;
            }
            if ((arr[i]==arr[j])&&(i<j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i-1>start) {
            arr=qsort(arr,start,i-1);
        }
        if (j+1<end) {
            arr=qsort(arr,j+1,end);
        }
        return (arr);
    }
}
