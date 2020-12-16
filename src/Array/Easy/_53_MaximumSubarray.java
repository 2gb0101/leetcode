package Array.Easy;

//Offer：数组
//Target：返回最大子数组的和
public class _53_MaximumSubarray {
//    解法一（分治法）：
//    不停的把数组一分为二
//    通过比较数组左右两边最大子数组的值，取大者得到最终结果
//    时间复杂度：O(nlgn)
    public int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }

    public int helper(int[] nums, int left, int right) {
        if (left >= right)
            //这里不能写nums[right]，因为if的条件是left >= right，即right是小的那个数
            //由于是小的那个数，在向左分治的过程中，right有可能变成负数，而left却一直正数
            //所以这里必须得写nums[left]
            return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);
        int rmax = helper(nums, mid + 1, right);
        int mmax = nums[mid], t = mmax;


        //因为这次的要求是查找最大子数组的和值
        //所以在分治后，还要计算分治前的整个数组的和值，来比较到底哪个和值是最大的
        //所以这一部分必不可少
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }

        return Math.max(mmax, Math.max(lmax, rmax));
    }
//    解法二（循环遍历法）：
//    创建两个变量
//    一个用于记录要遍历至此时，得到的最大sum值
//            一个用于实时计算最大sum值
//    比较两个变量的值大小，对最大sum值进行更新
//    时间复杂度：O(n)
    public int maxSubArray2(int[] nums) {
        int res = Integer.MIN_VALUE, curSum = 0;
        for (int num : nums) {
            curSum = Math.max(curSum + num, num);
            res = Math.max(res, curSum);
        }
        return res;
    }
}
