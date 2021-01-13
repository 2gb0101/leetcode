package Array.Easy;

//Offer：给定一个长度为2n的整数数组
//Target：分割数组，两两一对，让每对中较小的数的和最大

import java.util.Arrays;

public class D12_561_ArrayPartition {
//    贪婪算法：
//    由于我们要最大化每对中的较小值之和
//    那么肯定是每对中两个数字大小越接近越好
//    因为如果差距过大，而我们只取较小的数字，那么大数字就浪费掉了
//
//    实现：
//    给数组排个序，按顺序的每两个就是一对
//    取出每对中的第一个数即为较小值累加起来即可
    public int arrayPairSum(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
