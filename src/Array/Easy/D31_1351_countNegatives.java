package Array.Easy;

//Offer：一个 m * n 的矩阵 grid
//        矩阵中的元素无论是按行还是按列，都以非递增顺序排列
//Target：统计并返回 grid 中 负数 的数目
public class D31_1351_countNegatives {
//    解题思路1
//    利用矩阵中的元素无论是按行还是按列，都以非递增顺序排列这个条件
    public int countNegatives1(int[][] grid) {
        int count = 0;
        for(int i=0;i<grid.length;i++){
            int[] nums = grid[i];
            for(int j =0;j<nums.length;j++){
                if(nums[j]<0){
                    //统计每行中为负数的个数 并对所有行累加
                    count = count+(nums.length-j);
                    break;
                }
            }
        }
        return count;
    }


//    解题思路2：二分查找
//    利用数据已排序的特点，结合二分查找的高效，可以批量统计 cnt
//
//    复杂度分析
//    时间复杂度 O(mlog(n))
//    空间复杂度 O(1)
    public int countNegatives2(int[][] grid) {
        int count = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            int[] row = grid[i];
            if (row[n - 1] >= 0) continue; // 整行非负，跳过
            if (row[0] < 0) { // 整行负数
                count += (m - i) * n; // 后面的行也计入
                break; // 无需再继续遍历
            }
            int first = _binarySearch(row); // 当前行二分查找第一个小于 0 的数的索引
            count += n - first;
        }
        return count;
    }

    // 查找第一个小于 0 的数的索引
    private int _binarySearch(int[] arr) {
        int begin = 0, end = arr.length;
        while (begin < end) {
            int mid = begin + ((end - begin) >> 1);
            if (arr[mid] >= 0)
                begin = mid + 1;
            else {
                end = mid;
            }
        }
        return end;
    }


//    解题思路3：解法2的改进
//    充分利用题目条件：
//    行非增序列，故在找每行第一个负数时可用二分查找（比线性查找快）；
//    列也是非增序列，故遍历每行时可以以上一行的第一个负数的索引作为右边界。
    public int countNegatives3(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int num=0,right=n,left=0;
        for(int i=0;i<m;i++){
            // right=left;有没有这句都行，因为上一次循环结束时这句必然成立。
            // left记录的就是第一个负数的位置
            left=0;
            while(left<right){
                int middle=(left+right)/2;
                if(grid[i][middle]>=0){
                    left=middle+1;
                }else{
                    right=middle;
                }
            }
            num+=(n-left);
        }
        return num;
    }

}
