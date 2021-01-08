package Array.Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Offer:
//    一个包含 n 个整数的数组 nums ，一个目标值 target
//Target:
//    判断 nums 中是否存在四个元素 a，b，c 和 d
//    使得 a + b + c + d 的值与 target 相等
//    找出所有满足条件且不重复的四元组
//    注意：答案中不可以包含重复的四元组
public class D2_18_FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int four = 0 ; four < nums.length - 3; four++){
            if(four > 0 && nums[four] == nums[four - 1]) continue;
            for(int three = four + 1; three < nums.length - 2;three++){
                if(three > four + 1 && nums[three] == nums[three - 1]) continue;
                int l = three + 1,r = nums.length - 1;
                while(l < r){
                    int sum = nums[four] + nums[three] + nums[l] + nums[r];
                    if(sum == target){
                        List<Integer> anew = new ArrayList<Integer>();
                        anew.add(nums[four]);
                        anew.add(nums[three]);
                        anew.add(nums[l]);
                        anew.add(nums[r]);
                        //写法1：
                        res.add(anew);
                        while(l< r && nums[l] == nums[l+1]) l++;
                        while(l< r && nums[r] == nums[r-1]) r--;
                        //写法2，但不推荐：
//                        if(!res.contains(out))
//                            res.add(out);
                        l++;r--;
                    }
                    else if (sum < target) l++;
                    else r--;
                }
            }
        }
        return res;
    }
}
