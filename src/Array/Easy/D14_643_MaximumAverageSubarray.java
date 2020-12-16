package Array.Easy;

//Offer：一个数组nums，一个数字k
//Target：
//    找长度为k且平均值最大的子数组
//    子数组必须是连续的

import java.util.Arrays;

public class D14_643_MaximumAverageSubarray {

//    解法一（建立累加数组）：
//      子数组必须是连续的
//            即，不能给数组排序
//      计算子数组之和的常用方法
//            建立累加数组
    public double findMaxAverage1(int[] nums, int k) {
        int n = nums.length;
        int[] sums = Arrays.copyOf(nums,n);
        for (int i = 1; i < n; ++i) {
            sums[i] = sums[i - 1] + nums[i];
        }
        double mx = sums[k - 1];
        for (int i = k; i < n; ++i) {
            mx = Math.max(mx, (double)sums[i] - sums[i - k]);
        }
        return mx / k;
    }
//    滑动窗口法：
//      由于这道题子数组的长度k是确定的
//      所以其实没有必要建立整个累加数组
//      可以先算出前k个数字的和
//      就像维护一个滑动窗口一样
    public double findMaxAverage2(int[] nums, int k) {
        double sum = 0;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        double res = sum;
        for (int i = k; i < nums.length; ++i) {
            sum += nums[i] - nums[i - k];
            res = Math.max(res, sum);
        }
        return res / k;
    }

}
