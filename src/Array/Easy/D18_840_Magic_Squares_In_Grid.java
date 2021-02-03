package Array.Easy;

//Offer：
//    定义魔方是一个3x3大小的矩阵
//    由1到9中到数字组成的
//    各行各列即对角线和都必须相等
//    给一个矩阵
//Target：判断给的矩阵中有多少个魔方
public class D18_840_Magic_Squares_In_Grid {
//    根据魔方的定义
//        魔方各行各列及对角线之和已经被限定必须为15
//        且最中间的位置必须是5
//        否则根本无法组成满足要求的正方形

//    证明如下：
//    把9个数字按照自然阅读顺序编号1~9.
//    中间的数字总共参与了四次求和
//    C1+C5+C9=C3+C5+C7=C2+C5+C8=C4+C5+C6
//    从等式的基本性质推出
//    除了中间的数字剩下的8个数字分成了和相等的四对
//    反向思考
//        如果中间是5
//        四周相对位置的数字的组合将是固定的
//        那么优化空间肯定挺大
//        假设除中间外8个数字中最小的为min，最大的为max
//        假设min和max不在一组
//        此时不难发现含有max的一组肯定大于含有min的一组
//        （设min和ci一组，max和cj一组；此时min<cj,ci<max）
//        所以剩下的8个数字最大和最小的肯定在一组。
//        如果中间的数字不为1或9，那么1和9肯定在一组
//        同理2和8必须在一组
//        （中间只能有一个数字，2和8不能同时在中间）
//        这个逻辑还可以向中间挤压
//        则只要中间不是5（对称中心），那么总有一个数字必须落单
//        假设各行各列及对角线之和为x
//        那么含x的一行一列两对角线总和为
//        1+2+...+9+3*5 = 4*x
//        可得x=15

//    再根据上面的思路，用暴力法解
//    遍历所有的3x3大小的正方形
//    写子函数来检测各行各列及对角线的和是否为15
//    调用子函数之前
//    先检测一下中间的数字是否为5
//        是的话再进入子函数
//    在子函数中
//        先验证，该正方形中的数字是否只有1到9中的数字，且不能由重复出现
//        使用一个一维数组来标记出现过的数字
//        若当前数字已经出现了，直接返回false
//        计算各行各列及对角线之和是否为15
//        若全部为15 ，则返回true
    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int i = 0; i < m - 2; ++i) {
            for (int j = 0; j < n - 2; ++j) {
                if (grid[i + 1][j + 1] == 5 && isValid(grid, i, j)) ++res;
            }
        }
        return res;
    }
        boolean isValid(int[][] grid, int i, int j) {
            int[] cnt = new int[10];
            for (int x = i; x < i + 2; ++x) {
                for (int y = j; y < j + 2; ++y) {
                    int k = grid[x][y];
                    if (k < 1 || k > 9 || cnt[k] == 1)
                        return false;
                    cnt[k] = 1;
                }
            }
            if (15 != grid[i][j] + grid[i][j + 1] + grid[i][j + 2]) return false;
            if (15 != grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2]) return false;
            if (15 != grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2]) return false;
            if (15 != grid[i][j] + grid[i + 1][j] + grid[i + 2][j]) return false;
            if (15 != grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1]) return false;
            if (15 != grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2]) return false;
            if (15 != grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2]) return false;
            if (15 != grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2]) return false;
            return true;
        }
}
