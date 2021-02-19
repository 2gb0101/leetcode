package DynamicProgramming.Easy;

//Offer: 给定一个整数数组  nums
//Target: 求数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点
public class D34_303_NumArray {
//    解法1：暴力法
//    第一感觉是使用暴力法
//    O(N) 的时间复杂度，很快的嘛！
//    但是可能因为题目说【会多次调用 sumRange 方法】，其实耗时挺久
    class NumArray1 {
        int[] nums;
        public NumArray1(int[] nums) {
            this.nums = nums.clone();
        }

        public int sumRange(int i, int j) {
            int sum = 0;
            while (i <= j) {
                sum += nums[i++];
            }
            return sum;
        }
    }

//    解法2：辅助数组
//    多次调用 sumRange 方法果然够狠！然后想了想，动规的思想不就是存储中间值吗？
//    得，先搞个表，到时候直接返回岂不是美滋滋？
//    然后 超出内存限制 ，我又傻了！
    class NumArray2 {
        int[][] ret;
        public NumArray2(int[] nums) {
            ret = new int[nums.length][nums.length];
            for (int left = 0; left < nums.length; left++) {
                for (int right = left; right < nums.length; right++) {
                    if (left == right) ret[left][right] = nums[left];
                    else
                        ret[left][right] = ret[left][right-1] + nums[right];
                }
            }
        }

        public int sumRange(int i, int j) {
            return ret[i][j];
        }
    }

//    解法3：动态规划（前缀和）
    class NumArray3 {
        int[] sum;
        public NumArray3(int[] nums) {
            sum = new int[nums.length+1];
            for (int i = 0; i < nums.length; i++) {
                sum[i+1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j+1] - sum[i];
        }
    }
}
