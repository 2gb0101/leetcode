package Array.Easy;

import java.util.*;

//Offer: 给你一个数组 nums
//Target: 对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目
//        以数组形式返回答案
public class D32_1365_smallerNumbersThanCurrent {
//    解法1：暴力法
//    时间复杂度 O(n^2)
//    空间复杂度 O(n)
    public int[] smallerNumbersThanCurrent1(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) { // 枚举所有元素
            for (int j = 0; j < len; j++) { // 枚举其他元素
                if (i == j) continue;
                if (nums[i] > nums[j]) res[i]++;
            }
        }
        return res;
    }

//    解法2：排序 + 映射
//    时间复杂度 O(nlog(n))
//    空间复杂度 O(n)
    public int[] smallerNumbersThanCurrent2(int[] nums) { // 8, 1, 2, 2, 3
        int len = nums.length;
        Map<Integer, Set<Integer>> valueIndex = new HashMap<>(len); // 预存每个值与索引对应
        for (int i = 0; i < len; i++) {
            if (!valueIndex.containsKey(nums[i])) valueIndex.put(nums[i], new HashSet<>());
            valueIndex.get(nums[i]).add(i);  // set中存放当前值的索引值，等循环结束后，set中存放的是同个值出现的所有索引位置
        }
        int[] sortedArr = Arrays.copyOf(nums, len), res = new int[len];
        Arrays.sort(sortedArr); // 1, 2, 2, 3, 8
        for (int si = len - 1; si >= 0; si--) { // 倒序，方便处理同值的情况
            // 你的索引是多少，就有多少个数字小于你，si就是当前的索引值
            // 严格说应该是 小于等于你
            for (int i : valueIndex.get(sortedArr[si]))
                res[i] = si; // 同值的所有索引都更新
        }
        return res;
    }

//    解法3：计数排序（使用桶思想）
//    时间复杂度 O(n)
//    空间复杂度 O(1)
//    空间复杂度 int[] freq
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 统计出现频率 frequency
        // 因题意限制 0 <= nums[i] <= 100 而定，不随数据量大小而改变
        int[] freq = new int[101]; // 索引即数值
        for (int num : nums) freq[num]++;

        // 对频率(而非对原数组nums)从前到后累加
        for (int i = 1; i < freq.length; i++) {
            freq[i] = freq[i] + freq[i - 1];
        }

        // 输出结果
        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            if (nums[i] > 0) res[i] = freq[nums[i] - 1];
        }
        return res;
    }

//    思考扩展
//    思考这样一个问题：全国高考考生(大量元素)按成绩(0-750)排名问题
//    如何排序，可参考计数排序——特殊的桶排序
//            计数排序
//    大体思想：扫描一遍所有人，按分数分到不同给的桶里，桶内无需再排序，仅需统计各桶内数量
//    适用性：数据量大、数据范围不大的情况
//    假设数据已排序如 [748, 748, 747, ..., 730, 730, ..., 730, 729, ...]
//    问：有多少人在 729 前面？（同分同名次，如 747 是第三名，没有第二名）
//    答：即 第一个 729 的索引
}
