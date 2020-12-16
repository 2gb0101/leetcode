package Array.Easy;

//Offer：
//    一个表示图像灰度的2D整数矩阵M
//Target：
//    设计一个更平滑的图像
//    使每个单元的灰度变为周围所有8个单元及其自身的平均灰度
//    如果一个单元格周围的单元格少于8个，则请尽可能多地使用
public class D14_661_ImageSmoother {
//    有图像处理背景的：用算子来跟图片进行卷积
//    但是由于这道题只是个Easy的题目，直接用土办法就能解
//        直接对于每一个点统计其周围点的个数
//        累加像素值，做个除法就行
//        注意边界情况的处理
    public int[][] imageSmoother(int[][] M) {
        int m = M.length, n = M[0].length;
        int[][] res = new int[m][n];
        //这个dir数组就是用来模拟周边的八个点,见上图
        int[][] dirs = new int[][]{
                {0,-1},{-1,-1},{-1,0},{-1,1},
                {0,1},{1,1},{1,0},{1,-1}};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int cnt = M[i][j], all = 1;
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    ++all;
                    cnt += M[x][y];
                }
                res[i][j] = cnt / all;
            }
        }
        return res;
    }
}
