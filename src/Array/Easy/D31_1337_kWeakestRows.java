package Array.Easy;

import java.util.Arrays;

//Offer：一个大小为 m * n 的方阵 mat，方阵由若干军人和平民组成，分别用 1 和 0 表示
//Target：返回方阵中战斗力最弱的 k 行的索引，按从最弱到最强排序
//        如果第 i 行的军人数量少于第 j 行，或者两行军人数量相同但 i 小于 j，那么我们认为第 i 行的战斗力比第 j 行弱
//        军人 总是 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前
//
//示例 1：
//    输入：mat =
//    [[1,1,0,0,0],
//    [1,1,1,1,0],
//    [1,0,0,0,0],
//    [1,1,0,0,0],
//    [1,1,1,1,1]],
//    k = 3
//    输出：[2,0,3]
//    解释：
//    每行中的军人数目：
//    行 0 -> 2
//    行 1 -> 4
//    行 2 -> 1
//    行 3 -> 2
//    行 4 -> 5
//    从最弱到最强对这些行排序后得到 [2,0,3,1,4]
public class D31_1337_kWeakestRows {
//    解题思路1：辅助数组+索引偏移量
//    将每一行的士兵数量 x 100 + 索引存到一个新的列表里
//    对这个列表进行排序，取前面的k位对100取余即是需要的索引值
    public int[] kWeakestRows1(int[][] mat, int k) {
        int[] list = new int[mat.length];
        int[] result = new int[k];
        for(int i=0;i<mat.length;i++){
            list[i] = count(mat[i])*100+i;
            //要*100，是为了让count(mat[i])的结果远大于i，这样排序时就不会受到i的大小的影响
            //所以想*1000也是可以的，乘以其他数也是可以的，你喜欢就好
        }
        Arrays.sort(list);
        for(int i=0;i<k;i++){
            result[i] = list[i]%100;
        }
        return result;
    }

    public int count(int[] nums){
        int sum=0;
        for(int n:nums){
            if(n!=1){
                break;
            }
            sum+=n;
        }
        return sum;
    }


//    解题思路2：记录 0 的位置（枚举）
//    用一个二维数组 pair 统计每一行的 id 以及每一行数字 1 的数量；
//    然后排序，然后输出二维数组 pair 的前 k 个数组下标。
    public int[] kWeakestRows2(int[][] mat, int k) {

        int m = mat.length;
        int n = mat[0].length;
        int[][] pair = new int[m][2];

        for (int r = 0; r < m; r++) {
            pair[r][0] = r;                            //记录每一行的下标
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 1)  pair[r][1]++;     //记录每一行1的数量
            }
        }

        Arrays.sort(pair, (e1, e2) -> e1[1] - e2[1]);  //按1的数量升序排列
        int[] arr = new int[k];

        for (int i = 0; i < k; i++) {
            arr[i] = pair[i][0];                       //取前k个下标
        }
        return arr;
    }
//    复杂度分析
//    时间复杂度：O(m * n)O(m∗n)，n 为网格 mat 的行数，m 为列数。
//    空间复杂度：O(k + m)O(k+m)，

//    解题思路3：记录 0 的位置（二分优化）
//    1 总是出现在 0 之前。意味着每一行都是一个降序的有序数组。所以 1 的数量就是第一个 0 的下标的大小。
//    问题转化为寻找第一个0的下标。
    public int[] kWeakestRows3(int[][] mat, int k) {

        int m = mat.length;
        int n = mat[0].length;
        int[][] pair = new int[m][2];

        for (int r = 0; r < m; r++) {
            pair[r][0] = r;
            pair[r][1] = binary_search(mat[r], 0, n); // 查找第一个0的下标
        }

        Arrays.sort(pair, (e1, e2) -> e1[1] - e2[1]);
        int[] arr = new int[k];

        for (int i = 0; i < k; i++) {
            arr[i] = pair[i][0];
        }
        return arr;
    }
    // 查找第一个0的下标
    private int binary_search(int[] arr, int l, int r) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == 1)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
//    复杂度分析
//    时间复杂度：O(m × log(n))O(m×log(n))，
//    空间复杂度：O(k + m)O(k+m)，
}
