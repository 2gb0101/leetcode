package Array.Middle;

import java.util.Arrays;

//Offer：
//    给定一个包括n个整数的数组nums和一个目标值target
//Target：
//    找出nums中的三个整数，使得它们的和与target最接近
//    返回这三个数的和。假定每组输入只存在唯一答案
public class _16_ThreeSumClosest {
//    思路：
//    让返回这个最接近于给定值的值
//    即：保证当前三数和跟给定值之间的差的绝对值最小
    public int threeSumClosest(int[] nums, int target) {
        int closest = nums[0] + nums[1] + nums[2];
        int diff = Math.abs(closest - target);
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int newDiff = Math.abs(sum - target);
                if (diff > newDiff) {
                    diff = newDiff;
                    closest = sum;
                }
                if (sum < target) ++left;
                else --right;
            }
        }
        return closest;
    }
}
