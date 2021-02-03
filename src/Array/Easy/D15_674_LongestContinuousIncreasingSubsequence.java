package Array.Easy;

//Offer：一个未经排序的整数数组
//Target：找到最长且连续递增的子序列，并返回该序列的长度
public class D15_674_LongestContinuousIncreasingSubsequence {
//    辅助变量法：
    public int findLengthOfLCIS1(int[] nums) {
        int res = 0, cnt = 0, cur = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > cur) ++cnt;
            else cnt = 1;
            res = Math.max(res, cnt);
            cur = num;
        }
        return res;
    }

//    解法思路同上，每次都和前面一个数字来比较，注意处理无法取到前一个数字的情况
    public int findLengthOfLCIS2(int[] nums) {
        int res = 0, cnt = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || nums[i - 1] < nums[i])
                res = Math.max(res, ++cnt);
            else
                cnt = 1;
        }
        return res;
    }
}
