package Array.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Offer: 给你一个 m * n 的矩阵，矩阵中的数字各不相同
//Target: 请你按任意顺序返回矩阵中的所有幸运数
//        幸运数是指矩阵中满足同时下列两个条件的元素：
//        在同一行的所有元素中最小
//        在同一列的所有元素中最大
public class D32_1380_luckyNumbers {
//    解法：两个数组记录最大值和最小值
//    min数组存放每行最小值;
//    max数组存放每列最大值;
//    循环比较得到每一行的最小值和每一列的最大值。
//    然后比较第i行最小值和第j列最大值是否为同一个数，即min[i] == max[j]
    public List<Integer> luckyNumbers (int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] min = new int[m];
        int[] max = new int[n];
        Arrays.fill(min, Integer.MAX_VALUE);
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                min[i] = Math.min(min[i], matrix[i][j]);// 第i行最小值
                max[j] = Math.max(max[j], matrix[i][j]);// 每一列最大值与当前值比较
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (min[i] == max[j])
                    list.add(min[i]);
            }
        }

        return list;
    }
}
