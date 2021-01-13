package Array.Easy;

//Offer：给定一个整数数组和一个整数k
//Target：找出有多少对【不重复】的数对(i, j)，使得i和j的差刚好为k

import java.util.Arrays;
import java.util.HashMap;

public class D12_532_KdiffPairsInAnArray {
//    哈希表法：
//    由于k有可能为0，而只有含有至少两个相同的数字才能形成数对
//    那么就是说我们需要统计数组中每个数字的个数
//    可以建立每个数字和其出现次数之间的映射
//    然后遍历哈希表中的数字
//    如果k为0
//        该数字出现的次数大于1，则结果res自增1；
//    如果k不为0
//        用当前数字加上k后得到的新数字也在数组中存在
//        则结果res自增1
    public int findPairs1(int[] nums, int k) {
        int res = 0, n = nums.length;
        HashMap<Integer, Integer> m = new HashMap<Integer,Integer>();
        for (int num : nums){
            if(m.get(num) == null){
                m.put(num,1);
            }else{
                m.put(num,m.get(num) + 1);
            }
        }
        for (Integer key : m.keySet()) {
            if (k == 0 && m.get(key) > 1) ++res;
            if (k > 0 && m.get(key + k) != null) ++res;
        }
        return res;
    }

//    双指针法：
//    需要给数组排序，节省了空间的同时牺牲了时间
//    遍历排序后的数组
//    在当前数字之后找第一个和当前数之差不小于k的数字
//    若这个数字和当前数字之差正好为k，那么结果res自增1
//    然后遍历后面的数字去掉重复数字
    public int findPairs2(int[] nums, int k) {
        int res = 0, n = nums.length, j = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; ++i) {
            j = Math.max(j, i + 1);
            while (j < n && (long)nums[j] - nums[i] < k) ++j;
            if (j < n && (long)nums[j] - nums[i] == k) ++res;
            while (i < n - 1 && nums[i] == nums[i + 1]) ++i;
        }
        return res;
    }
}
