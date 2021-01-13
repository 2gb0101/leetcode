package Array.Easy;

//Offer：给定一个矩阵和两个整数（代表行数和列数）
//Target：实现矩阵大小的重塑
public class D12_566_ReshapeTheMatrix {
//    对于这种二维数组大小重新分配的问题
//    关键就是对应位置的坐标转换
//    最直接的办法：先把原数组拉直，变成一条直线，然后再组成新的数组

//    双循环法：
//    先判断给定数组是否能重塑成给定的大小
//        看两者的元素总数是否相同
//        直接行数乘以列数即可
//    新建一个目标大小的数组，并开始遍历
//        对于每个位置
//        先转为拉直后的一维坐标
//        再算出在原数组中的对应位置
//        赋值过来即可
    public int[][] matrixReshape1(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) return nums;
        int[][] res = new int[r][c];
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                int k = i * c + j;
                res[i][j] = nums[k / n][k % n];
            }
        }
        return res;
    }

//    单循环法：
//    直接遍历拉直后的一维数组的坐标
//    分别转换为两个二维数组的坐标进行赋值
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) return nums;
        int[][] res = new int[r][c];
        for (int i = 0; i < r * c; ++i) {
            res[i / c][i % c] = nums[i / n][i % n];
        }
        return res;
    }
}
