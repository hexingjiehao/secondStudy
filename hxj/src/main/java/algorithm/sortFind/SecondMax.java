package algorithm.sortFind;

/**
 * Created by xiongjie on 2018/12/1.
 * 在O(n)情况下，找出第二大的值
 */
public class SecondMax {

    public static void main(String[] args) {
        int[] arr={1,2,3,3,6,5,4};
        int res=getSecondBiggestNum(arr);
        System.out.println(res);
    }

    public static int getSecondBiggestNum(int []arr) {
        int temp =0;
        int biggestNum = arr[0];
        //第一轮迭代求最大值
        for (int i = 0; i < arr.length ; i++) {
            if (biggestNum < arr[i]) {
                biggestNum = arr[i];
            }
        }

        //第二轮迭代求第二大值
        for (int i = 0; i < arr.length; i++) {
            if (temp < arr[i] && arr[i] != biggestNum) {
                temp = arr[i];
            }
        }
        return temp;
    }


}
