package Array.Middle;

//Offer: 一个整数数组 nums
//Target: 找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）
//        返回该子数组所对应的乘积
public class D14_152_maxProduct {
//    解法1：动态规划
//    先定义一个数组 dpMax，用 dpMax[i] 表示以第 i 个元素的结尾的子数组，乘积最大的值，也就是这个数组必须包含第 i 个元素。
//    那么 dpMax[i] 的话有几种取值。
//    当 nums[i] >= 0 并且dpMax[i-1] > 0，dpMax[i] = dpMax[i-1] * nums[i]
//    当 nums[i] >= 0 并且dpMax[i-1] < 0，此时如果和前边的数累乘的话，会变成负数，所以dpMax[i] = nums[i]
//    当 nums[i] < 0，此时如果前边累乘结果是一个很大的负数，和当前负数累乘的话就会变成一个更大的数。
//        所以我们还需要一个数组 dpMin 来记录以第 i 个元素的结尾的子数组，乘积最小的值。
//        当dpMin[i-1] < 0，dpMax[i] = dpMin[i-1] * nums[i]
//        当dpMin[i-1] >= 0，dpMax[i] = nums[i]
//    当然，上边引入了 dpMin 数组，怎么求 dpMin 其实和上边求 dpMax 的过程其实是一样的。
//
//    按上边的分析，我们就需要加很多的 if else来判断不同的情况，这里可以用个技巧。
//    我们注意到上边dpMax[i] 的取值无非就是三种，dpMax[i-1] * nums[i]、dpMin[i-1] * nums[i] 以及 nums[i]。
//    所以我们更新的时候，无需去区分当前是哪种情况，只需要从三个取值中选一个最大的即可。
//    dpMax[i] = max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
//
//    求 dpMin[i] 同理。
//    dpMin[i] = min(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
//
//    更新过程中，我们可以用一个变量 max 去保存当前得到的最大值。

    public int maxProduct1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dpMax = new int[n];
        dpMax[0] = nums[0];
        int[] dpMin = new int[n];
        dpMin[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
            max = Math.max(max, dpMax[i]);
        }
        return max;
    }

    //优化空间复杂度
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dpMax = nums[0];
        int dpMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            //更新 dpMin 的时候需要 dpMax 之前的信息，所以先保存起来
            int preMax = dpMax;
            dpMax = Math.max(dpMin * nums[i], Math.max(dpMax * nums[i], nums[i]));
            dpMin = Math.min(dpMin * nums[i], Math.min(preMax * nums[i], nums[i]));
            max = Math.max(max, dpMax);
        }
        return max;
    }

//    解法2：分类讨论
//    仔细想一个这个题在考什么，我们先把题目简单化，以方便理清思路。
//    如果给定的数组全部都是正数
//        子数组最大的乘积 = 把所有的数字相乘
//    如果给定的数组存在负数
//        出现了偶数个负数：最大乘积 = 把所有的数字相乘
//        出现奇数个负数：我们真正需要解决的问题
//    如果给定的数组存在0
//        存在 0 ，会使得上边的代码到 0 的位置之后 max 就一直变成 0
//        解决该问题：把数组看成几个都不含有 0 的子数组进行解决即可
//    总结：乘积理论上乘的数越多越好，但前提是必须保证负数是偶数个
//    对于一个有奇数个负数的数组，最大数的取值情况就是两种
//        第一种：不包含最后一个负数的子数组
//        第二种：不包含第一个负数的子数组
//    我们无需写if-else语句分类讨论
//    和解法一一样，只要保证计算过程中包含了上边讨论的三种情况即可
//    对于负数是奇数个的情况，我们采用正着遍历，倒着遍历的技巧即可
    public int maxProduct3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 1;
        int res = nums[0];
        //包含了所有数相乘的情况
        //奇数个负数的情况一
        for (int i = 0; i < nums.length; i++) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }
        max = 1;
        //奇数个负数的情况二
        for (int i = nums.length - 1; i >= 0; i--) {
            max *= nums[i];
            res = Math.max(res, max);
            if (max == 0) {
                max = 1;
            }
        }
        return res;
    }
}
