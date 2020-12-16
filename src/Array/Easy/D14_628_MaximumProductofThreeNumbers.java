package Array.Easy;

import java.util.Arrays;

//Offer：一个整数数组
//Target：找数组中的三个数，使其乘积最大
public class D14_628_MaximumProductofThreeNumbers {
//    最简单：
//    直接排序，最后三个数字相乘
//    但被OJ无情打脸，没有考虑到负数和0的情况

//    分析：
//    题给了数组的范围，至少三个
//    如果是三个数的话，就无所谓了，直接相乘返回即可
//    但是如果超过了3个，而且有负数存在的话，情况就可能不一样
//    如果全是负数
//        三个负数相乘还是负数
//        为了让负数最大，那么其绝对值就该最小
//        而负数排序后绝对值小的都在末尾
//	    （其实跟全是正数的情况一样，排序后大的数都在末尾，让大数相乘）
//        所以是末尾三个数字相乘
//    如果前半段是负数，后半段是正数
//        最好的情况：
//            两个最小的负数相乘得到一个正数
//            再跟一个最大的正数相乘
//            这样得到的肯定是最大的数

//    暴力法：不讨论正负直接计算
    public int maximumProduct1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int p = nums[0] * nums[1] * nums[n - 1];
        return Math.max(p, nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

//    辅助变量法：O(n)的时间复杂度
    public int maximumProduct2(int[] nums) {
        int mx1 = Integer.MIN_VALUE, mx2 = Integer.MIN_VALUE, mx3 = Integer.MIN_VALUE;
        int mn1 = Integer.MAX_VALUE, mn2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > mx1) {
                mx3 = mx2; mx2 = mx1; mx1 = num;
            } else if (num > mx2) {
                mx3 = mx2; mx2 = num;
            } else if (num > mx3) {
                mx3 = num;
            }
            if (num < mn1) {
                mn2 = mn1; mn1 = num;
            } else if (num < mn2) {
                mn2 = num;
            }
        }
        return Math.max(mx1 * mx2 * mx3, mx1 * mn1 * mn2);
    }
}
