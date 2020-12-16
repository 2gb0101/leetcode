package Array.Middle;

import java.util.List;

//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
//例如，给定三角形：
//[
//     [2],
//   [3,4],
// [6,5,7],
//[4,1,8,3]
//]
//自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
//说明：
//如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
public class D14_120_minimumTotal {
//    解法1：自顶向下
//    换个角度看triangle
//        [2],
//       [3,4],
//      [6,5,7],
//     [4,1,8,3]
//
//     [2],
//     [3,4],
//     [6,5,7],
//     [4,1,8,3]

//    状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和

//    状态分析
//        初始化：
//        dp[0][0]=triangle[0][0]
//        常规：
//        triangle[i][j]一定会经过triangle[i-1][j]或者triangle[i-1][j-1],
//        所以状态dp[i][j]一定等于dp[i-1][j]或者dp[i-1][j-1]的最小值+triangle[i][j]
//        特殊：
//        triangle[i][0]没有左上角 只能从triangle[i-1][j]经过
//        triangle[i][row[0].length]没有上面 只能从triangle[i-1][j-1]经过

//    转换方程：dp[i][j]=min(dp[i-1][j],dp[i-1][j-1])+triangle[i][j]

    public int minimumTotal1(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;

        for (int i = 1; i < row; i++) {
            //对每一行的元素进行推导
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 最左端特殊处理
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        // dp最后一行记录了最小路径
        for (int i = 0; i < column; i++) {
            res = Math.min(res, dp[row - 1][i]);
        }
        return res;
    }

//    空间优化
//    观察自顶向下的代码会发现，对第i行的最小路径和的推导，只需要第i-1行的dp[i - 1][j]和dp[i - 1][j - 1]元素即可
//    可以使用两个变量暂存
//    一维的dp数组只存储第i行的最小路径和
    public int minimumTotal2(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // dp最大长度==triangle底边长度
        // 题意：只使用 O(n) 的额外空间（n 为三角形的总行数）
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);

        // prev暂存dp[i-1][j-1],cur暂存dp[i-1][j]
        int prev = 0, cur;
        for (int i = 1; i < triangle.size(); i++) {
            //对每一行的元素进行推导
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                cur = dp[j];
                if (j == 0) {
                    // 最左端特殊处理
                    dp[j] = cur + rows.get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[j] = prev + rows.get(j);
                } else {
                    dp[j] = Math.min(cur, prev) + rows.get(j);
                }
                prev = cur;
            }
        }

        int res = Integer.MAX_VALUE;
        // dp最后一行记录了最小路径
        for (int i = 0; i < triangle.size(); i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }

//    解法2：自底向上
//    状态定义：dp[i][j]表示包含第i行第j列元素的最小路径和
//    状态分析
//        初始化：
//        dp最后一行=triangle最后一行
//        常规：
//        triangle[i][j]一定会到达triangle[i+1][j]或者triangle[i+1][j+1],
//        所以状态dp[i][j]一定等于dp[i+1][j]或者dp[i+1][j+1]的最小值+triangle[i][j]
//    转换方程：dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
    public int minimumTotal3(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // 加1可以不用初始化最后一行
        // 根据题意，行列值相同
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + rows.get(j);
            }
        }
        return dp[0][0];
    }

//    空间优化
    public int minimumTotal4(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // dp中记录了求第i行时，第i+1的最小路径和
        int[] dp = new int[triangle.size() + 1];

        for (int i = triangle.size() - 1; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < rows.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + rows.get(j);
            }
        }
        return dp[0];
    }
}
