package Array.Easy;

//Offer：一个 M x N 的矩阵
//Target：
//    托普利茨矩阵：
//    矩阵的每一方向由左上到右下的对角线上具有相同元素
//    判断给定矩阵是否为托普利茨矩阵
public class D17_766_Toeplitz_Matrix {
//    按对角线遍历
//    注意题目说了，矩阵的行数列数不一定相等
//    要验证所有的对角线，其实本质是让我们斜向遍历矩阵，就是按照对角线来遍历矩阵
//    起点是最左下的数字
//    对于m x n的矩阵
//    最左下角数字的坐标为(m-1, 0)
//    然后开始往右下角遍历
//        先记录每条对角线左上角的数字为val
//        然后再往右下角遍历的时候
//        如果同一条对角线上的数字不等于val
//        直接返回false
//    当遍历完一条对角线的时候，切换一条对角线时
//        是根据起点数字的坐标移动的
//    细心观察发现
//    起点位置的移动：先从第一列往上移动，再从第一行往右移动
//    那么想要判断移动的方向，只要判断起点位置的行坐标是否为0即可
//    比如对于题目中的例子1
//        1 2 3 4
//        5 1 2 3
//        9 5 1 2
//    起点移动的方向是9 -> 5 -> 1 -> 2 -> 3 -> 4
    public boolean isToeplitzMatrix1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, p = m - 1, q = 0;
        while (p >= 0 && q < n) {
            int val = matrix[p][q], i = p, j = q;
            while (i + 1 < m && j + 1 < n) {
                if (matrix[++i][++j] != val) return false;
            }
            if(p > 0){
                --p;
            }else{
                ++q;
            }
        }
        return true;
    }

//    按正常顺序遍历：
//    对于每个遍历到的数字，都跟其右下方的数字对比
//    如果不相同，直接返回false即可
//    为了防止越界，不遍历最后一行和最后一列
//    遍历完成后，返回true
    public boolean isToeplitzMatrix2(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; ++i) {
            for (int j = 0; j < matrix[i].length - 1; ++j) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }
        return true;
    }
}
