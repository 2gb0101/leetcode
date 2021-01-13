package Array.Easy;

import java.util.Arrays;
import java.util.HashMap;

//Offer：一个整数数组
//Target：查找数组中是否包含重复值
public class D7_217_ContainsDuplicate {
//    哈希表法
//    遍历整个数组，如果哈希表里存在该数，返回true
//    如果不存在，则将其放入哈希表中
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (m.get(nums[i]) != null) return true;
            m.put(nums[i],i);
        }
        return false;
    }

//    排序法
//    先将数组排个序
//    比较相邻两个数字是否相等
//    时间复杂度取决于排序方法
    public boolean containsDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}
