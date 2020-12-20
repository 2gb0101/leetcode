package Array.Hard;

import java.util.Arrays;

public class D7_719_smallestDistancePair {
    public int smallestDistancePair(int[] nums, int k) {
        // 数组排序
        Arrays.sort(nums);
        int size = nums.length;

        // 最小距离的最小值
        int left = 0;
        // 最小距离的最大值
        int right = nums[size-1] - nums[0];
        while (left < right) {
            // 左中位数
            int mid = (left + right) >>> 1;
            if (findPairs(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 计算距离小于等于目标值的组合数
    private int findPairs (int[] nums, int target) {
        int size = nums.length;
        int count = 0;
        int end = 0;
        for (int i = 0; i < size; i++) {
            // 每次遍历的时候 end 指针不需要再重新复原到 i 的位置
            // 因为数组是有序的，即 i - (i-1) >= 0
            // 上一轮遍历的结果是 end - (i-1) <= K
            // 所以对于本轮来说，end - i <= K 一定成立
            // 所以继续从 end 的位置向后寻找即可
            while (end + 1 < size && nums[end+1] - nums[i] <= target) {
                ++end;
            }
            count += end - i;
        }
        return count;
    }
}
