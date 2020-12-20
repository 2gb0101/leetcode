package Array.Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Offer：一个可能包含重复元素的整数数组 nums
//Target：返回该数组所有可能的子集（幂集）
//说明：解集不能包含重复的子集
public class D12_90_subsetsWithDup {
//    解法1：回溯法
//    只需要判断当前数字和上一个数字是否相同，相同的话跳过即可。当然，要把数字首先进行排序。
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); //排序
        getAns(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            //和上个数字相等就跳过
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            getAns(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}
