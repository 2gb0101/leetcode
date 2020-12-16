package Array.Hard;

import java.util.HashMap;
import java.util.Map;

//Offer：矩阵 matrix，目标值 target
//Target：返回元素总和等于目标值的非空子矩阵的数量
//
//子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合
//如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同
public class D9_1074_numSubmatrixSumTarget {
//    解法：前缀和
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                //对于每行，计算前缀和prefixSum
                matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
            }
        }
        int res = 0;
        for (int i = 0; i < col; i ++) { //i和j分别代表两列
            for (int j = i; j < col; j ++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1); //这个必须有
                //上面这行是为了解决当cur - target=0，即cur=target时
                //map.get()获取的值必须为1，如果不写这行，根据后面的map.getOrDefault(cur - target, 0)
                //map.get()获取的值就是0了
                int cur = 0;
                for (int k = 0; k < row; k ++) {
                    //计算【第j列和第i - 1列两列之间的数量和】是否等于target
                    cur += matrix[k][j] - (i > 0 ? matrix[k][i - 1] : 0); //见图解
                    res += map.getOrDefault(cur - target, 0);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }
}
