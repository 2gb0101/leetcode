package Array.Easy;

//Offer：一个矩阵A
//Target：
//    对于矩阵的每一行
//    先将所有元素位置翻转一下
//    然后再按顺序将每个像素值取反

//Example 1:
//    Input:
//    [[1,1,0],
//    [1,0,1],
//    [0,0,0]]
//    Explanation: First reverse each row:
//    [[0,1,1],
//    [1,0,1],
//    [0,0,0]].
//    Then, invert the image:
//    [[1,0,0],
//    [0,1,0],
//    [1,1,1]]
public class D18_832_Flipping_an_Image {
//    暴力法（修改原数组）：
//    按照题目要求一步步做
    public int[][] flipAndInvertImage(int[][] A) {
        int C = A[0].length;
        for (int[] row: A)
            //(C + 1) / 2
            //对于偶数数组遍历一半元素
            //对于奇数数组遍历一半 + 1元素
            for (int i = 0; i < (C + 1) / 2; ++i) {
                //每个数字或上1,就达到取反的目的
                int tmp = row[i] ^ 1;
                row[i] = row[C - 1 - i] ^ 1;
                row[C - 1 - i] = tmp;
            }
        return A;
    }

//    不修改原数组
//    虽然暴力法直接了当，但是有时候不希望修改原数组
//    新建一个跟A一样长的二维数组
//        里面的各行还是空的
//        遍历A数组的各行
//    遍历各行上的数字时，采用从后往前的遍历顺序
//        对于每个数字取反在加入结果res中
//        这样直接将翻转和取反同时完成了
    public int[][] flipAndInvertImage2(int[][] A) {
        int row = A.length,col = A[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = col - 1; j >= 0; --j) {
                //因为Java中不支持这种写法res[i][col - j] = !A[i][j];
                res[i][col - 1 - j] = A[i][j] ^ 1;
            }
        }
        return res;
    }
}
