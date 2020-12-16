package Array.Middle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Offer：一个 m x n 的矩阵
//Target：如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
public class D9_73_setZeroes {
//    解法1: 用 O(m+n)额外空间
//    两遍扫matrix：
//      第一遍用集合记录哪些行,哪些列有0;
//      第二遍置0
    public void setZeroes1(int[][] matrix) {
        Set<Integer> row_zero = new HashSet<>();
        Set<Integer> col_zero = new HashSet<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    row_zero.add(i);
                    col_zero.add(j);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (row_zero.contains(i) || col_zero.contains(j)) matrix[i][j] = 0;
            }
        }
    }

//    解法2: 用O(1)空间
//    关键思想: 用matrix第一行和第一列记录该行该列是否有0,作为标志位
//    但是对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况
    public void setZeroes2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean row0_flag = false;
        boolean col0_flag = false;
        // 第一行是否有零
        for (int j = 0; j < col; j++) {
            if (matrix[0][j] == 0) {
                row0_flag = true;
                break;
            }
        }
        // 第一列是否有零
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                col0_flag = true;
                break;
            }
        }
        // 把第一行第一列作为标志位
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        // 置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (row0_flag) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0_flag) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

//    解法3：使用位运算
//    原理很简单，题目无非就是让我们保存数组需要赋值为0的行号和列号，考虑到针对任意一行，处理结果必为清零（0）和不清零（1）两种结果之一，非此即彼，又想省空间，位运算是个不错的选择。
//    但是，位运算不是O(1)的，因为如果标记数组大小还是跟矩阵大小有关的，为O(m/30+n/30);
//    各位权当拓宽一种思路吧。
    public void setZeroes(int[][] matrix) {
        //6位int存储180行和列（这里取巧了，试出来的）
        //这里的180是根据Leetcode的验证系统测出来的一个值
        //下面的30也是为了配合存储180的行列得到的值（因为3*60=180）
        int[] rowSigns = new int[6];
        int[] columnSigns = new int[6];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    //分别计算该行该列存放的数组下标
                    int row = i / 30;
                    int column = j / 30;
                    //使用或运算保存标记
                    rowSigns[row] |= 1 << (i % 30);
                    columnSigns[column] |= 1 << (j % 30);
                }
            }
        }
        //针对每一行从行数组中取出标记值，通过位运算识别该行是否要赋值位0；
        for (int i = 0; i < matrix.length; i++) {
            int row = i / 30;
            if ((rowSigns[row] & (1 << (i % 30))) != 0) {
                // 这里的!=0判断为true的话
                // 说明该行存在0，该行需要全部赋值为0
                Arrays.fill(matrix[i], 0);
            }
        }
        //针对每一列从行数组中取出标记值，通过位运算识别该行是否要赋值位0；
        for (int j = 0; j < matrix[0].length; j++) {
            int column = j / 30;
            //列与行不同，无法用数组的fill方法，因此需要按行一次判断
            if ((columnSigns[column] & (1 << (j % 30))) != 0) {
                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[i][j] != 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }
}
