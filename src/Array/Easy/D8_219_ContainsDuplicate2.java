package Array.Easy;

import java.util.HashMap;

//Offer ：一个整数数组和一个整数k
//Target ：找出是否存在nums [i] = nums [j]，并且i与j之间的差最大为k
//思路：哈希表法
public class D8_219_ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer,Integer> m = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (m.get(nums[i]) != null && i - m.get(nums[i]) <= k)
                return true;
            else
                m.put(nums[i],i);
        }
        return false;
    }
}
