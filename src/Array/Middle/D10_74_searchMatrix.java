package Array.Middle;

//Offer：已知一个具有下列特性的 m x n 矩阵：
//    每行中的整数从左到右按升序排列
//    每行的第一个整数大于前一行的最后一个整数
//Target：判断其内部是否存在目标值target
public class D10_74_searchMatrix {

//    解法1：递进式筛选，从右上角开始比对
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0){
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0; // 代表行
        int j = n - 1; // 代表列
        // 从右上角开始，比较target与右上角的数据的大小
        // 如果大于target，就可以往左进一行
        // 如果小于target，就可以往下走一行
        while(i<m && j>= 0){
            if (matrix[i][j] > target){
                j--;
            }else if (matrix[i][j] < target){
                i++;
            }else {
                return true;
            }
        }
        return false;
    }

//    解法2：排除法（一次二分）
    public boolean searchMatrix2(int[][] matrix, int target) {
        // 解法：一次二分查找
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int row = matrix.length;
        int col = matrix[0].length;
        // 可以理解为二维数组转换为一个一维数组，right就是最后一个元素。
        int left = 0, right = row * col - 1;
        int mid = 0;
        while (left < right) {
            mid = left + (right - left) / 2;
            // 重点：借助行数，将一维的索引值转换回二维坐标 行：mid / col, 列：mid % col。
            // 如果这一点比target小，那么mid和mid左边的值都不可能是解
            if (matrix[mid / col][mid % col] < target) {
                // 将区间转换为[mid + 1, right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        //判断最后结束位置是不是target，如果是则true，不是则false。
        if (matrix[left / col][left % col] != target) return false;
        return true;
        // 还有一种解法用两次二分：
        // 先对每一行的第一个值进行二分，找比target小的第一个数，再对这一行进行二分找最后的结果
    }
}
