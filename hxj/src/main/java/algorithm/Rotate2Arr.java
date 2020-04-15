package algorithm;

/**
 * 旋转二维数组.他是他是正方形。
 * Created by xiongjie on 2020/4/8.
 */
public class Rotate2Arr {

    public static void main(String[] args) {
        int[][] arr=new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };

        rotate(arr);
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n-1-i; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }

        for (int i=0;i<n;i++){
            int len=matrix[i].length;
            for (int j=0;j<len;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

}
