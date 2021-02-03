package Array.Easy;

//Offer：给一个矩阵
//Target：转置该矩阵
public class D19_867_Transpose_Matrix {
//    在大学的线性代数中，转置非常常见
//    矩阵转置
//        把 m x n 的矩阵变为 n x m 的
//        把原本在 A[i][j] 位置的数字变到 A[j][i] 上即可
//    实现：
//    由于此题限定了矩阵的大小范围为 [1, 1000]
//        所以不存在空矩阵的情况
//        因而不用开始时对矩阵进行判空处理
//        直接去获取矩阵的宽和高即可
//    因为之前说了转置会翻转原矩阵的宽和高
//        所以新建一个 n x m 的矩阵
//        然后遍历原矩阵中的每个数
//        将他们赋值到新矩阵中对应的位置上即可
    public int[][] transpose(int[][] A) {
        int m = A.length, n = A[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[j][i] = A[i][j];
            }
        }
        return res;
    }
}
