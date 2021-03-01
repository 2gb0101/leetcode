package DynamicProgramming.Middle;

//Offer: 一个由 0 和 1 组成的二维矩阵
//Target: 找到只包含 1 的最大正方形，并返回其面积
public class D37_221_maximalRectangle {
//    解法1：暴力
//    怎么找出这样的矩阵呢？
//    先回顾一下85题的思路：
//    如果我们知道了以这个点结尾的连续 1 的个数的话，问题就变得简单了：
//        求出高度是1的矩形面积
//        求出高度是2的矩形面积
//        求出高度是3的矩形面积
//        ...
//    以此类推，遍历所有的点，求出所有的矩阵就可以了
    public int maximalRectangle0(char[][] matrix) {  //85题的代码
        if (matrix.length == 0) {
            return 0;
        }
        //保存以当前数字结尾的连续 1 的个数
        int[][] width = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        //遍历每一行
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                //更新 width
                if (matrix[row][col] == '1') {
                    if (col == 0) {
                        width[row][col] = 1;
                    } else {
                        width[row][col] = width[row][col - 1] + 1;
                    }
                } else {
                    width[row][col] = 0;
                }
                //记录所有行中最小的数
                int minWidth = width[row][col];
                //向上扩展行
                for (int up_row = row; up_row >= 0; up_row--) {
                    int height = row - up_row + 1;
                    //找最小的数作为矩阵的宽
                    minWidth = Math.min(minWidth, width[up_row][col]);
                    //更新面积
                    maxArea = Math.max(maxArea, height * minWidth);
                }
            }
        }
        return maxArea;
    }

//    本题的做法：在85题的代码基础上，做这道题
//    下边的代码一定程度上已经做了一些优化
//    把能提前结束的地方提前结束了
    public int maximalSquare1_1(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        //保存以当前数字结尾的连续 1 的个数
        int[][] width = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        /************修改的地方*****************/
        int maxHeight = 0; //记录当前正方形的最大边长
        /*************************************/

        //遍历每一行
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                // 更新 width
                if (matrix[row][col] == '1') {
                    if (col == 0) {
                        width[row][col] = 1;
                    } else {
                        width[row][col] = width[row][col - 1] + 1;
                    }
                } else {
                    width[row][col] = 0;
                }
                // 记录所有行中最小的数
                int minWidth = width[row][col];

                /************修改的地方*****************/
                if(minWidth <= maxHeight){
                    continue;
                }
                /*************************************/

                // 向上扩展行
                for (int up_row = row; up_row >= 0; up_row--) {
                    int height = row - up_row + 1;
                    // 找最小的数作为矩阵的宽
                    minWidth = Math.min(minWidth, width[up_row][col]);

                    /************修改的地方*****************/
                    //因为我们找正方形，当前高度大于了最小宽度，可以提前结束
                    if(height > minWidth){
                        break;
                    }
                    // 只有是正方形的时候才更新面积
                    if (height == minWidth) {
                        maxArea = Math.max(maxArea, height * minWidth);
                        maxHeight = Math.max(maxHeight, height);
                        break;
                    }
                    /*************************************/
                }
            }
        }
        return maxArea;
    }

//    当然因为我们只考虑正方形，我们可以抛开原来的代码，只参照之前的思路写一个新的代码
//    首先因为正方形的面积是边长乘边长，所以上边的 maxArea 是没有意义的，我们只记录最大边长即可
//    然后是其它细节的修改，让代码更简洁，代码如下
    public int maximalSquare1_2(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        // 保存以当前数字结尾的连续 1 的个数
        int[][] width = new int[matrix.length][matrix[0].length];
        // 记录最大边长
        int maxSide = 0;
        // 遍历每一行
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                // 更新 width
                if (matrix[row][col] == '1') {
                    if (col == 0) {
                        width[row][col] = 1;
                    } else {
                        width[row][col] = width[row][col - 1] + 1;
                    }
                } else {
                    width[row][col] = 0;
                }
                // 当前点作为正方形的右下角进行扩展
                int curWidth = width[row][col];
                // 向上扩展行
                for (int up_row = row; up_row >= 0; up_row--) {
                    int height = row - up_row + 1;
                    if (width[up_row][col] <= maxSide || height > curWidth) {
                        break;
                    }
                    maxSide = Math.max(height, maxSide);
                }
            }
        }
        return maxSide * maxSide;
    }


//    解法2：动态规划
//    解法一中我们求每个点的最大边长时，没有考虑到之前的解，事实上之前的解完全可以充分利用
//    用 dp[i][j] 表示以 matrix[i][j] 为右下角正方形的最大边长那么递推式如下:
//    初始条件
//        就是第一行和第一列的 dp[i][j] = matrix[i][j] - '0'，也就意味着 dp[i][j] 要么是 0 要么是 1
//    递推式
//        dp[i][j] = Min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1]) + 1
//        也就是当前点的左边，上边，左上角的三个点中选一个最小值，然后加 1
//        首先要明确 dp[i][j] 表示以 matrix[i][j] 为右下角的正方形的最大边长
//        然后我们从当前点向左和向上扩展
//        向左最多能扩展多少呢？
//            dp[i][j-1] 和 dp[i-1][j-1]，当前点左边和左上角选一个较小的
//            也就是它左边最大的正方形和它左上角最大的正方形的，边长选较小的
//        向上能能扩展多少呢？
//            dp[i-1][j] 和 dp[i-1][j-1]，当前点上边和左上角选一个较小的
//            也就是它上边最大的正方形和它左上角最大的正方形，边长选较小的
//        然后向左扩展和向上扩展两个最小值中再选一个较小的，最后加上 1 就是最终的边长了
//        最终其实是从三个正方形中最小的边长
//    代码的话，使用个技巧，那就是行和列多申请一行，这样的话第一行和第一列的情况就不需要单独考虑了
    public int maximalSquare2(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxSide = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                //因为多申请了一行一列，所以这里下标要减 1
                if (matrix[i - 1][j - 1] == '0') {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    maxSide = Math.max(dp[i][j], maxSide);
                }
            }
        }
        return maxSide * maxSide;
    }


//    然后又是动态规划的经典操作了，空间复杂度的优化
//    因为更新当前行的时候，只用到前一行的信息，之前的行就没有再用到了，所以我们可以用一维数组，不需要二维矩阵
//    下边是空间复杂度优化的代码，最关键的是用 pre 保存了左上角的值
//  因为更新当前行的时候，只用到前一行的信息，之前的行就没有再用到了
//  所以我们可以用一维数组，不需要二维矩阵
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[] dp = new int[cols + 1];
        int maxSide = 0;
        int pre = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '0') {
                    dp[j] = 0;
                } else {
                    //在Math.min(dp[j], pre)中，此时的dp[j]保存的是上一行的dp[j]
                    //dp[j - 1]因为已经在本轮循环中更新了，所以代表的就是本行的dp[j - 1]
                    //pre 保存了左上角的值，即dp[i-1][j-1]
                    //即下面这行的代码，其实等价于
                    //dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i-1][j], pre)) + 1;
                    dp[j] = Math.min(dp[j - 1], Math.min(dp[j], pre)) + 1;
                    maxSide = Math.max(dp[j], maxSide);
                }
                pre = temp;
            }
        }
        return maxSide * maxSide;
    }
}
