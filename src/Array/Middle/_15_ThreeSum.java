package Array.Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Offer：
//    给定一个包含 n 个整数的数组nums
//Target：
//    判断nums中是否存在三个元素 a，b，c ,使得a + b + c = 0
//    找出所有满足条件且不重复的三元组
//    答案中不可以包含重复的三元组
public class _15_ThreeSum {
//    思路：
//    依题意，除了三个数全是0的情况之外，肯定会有负数和正数
//    要先 fix 一个数，然后去找另外两个数，找到两个数且和为第一个 fix 数的相反数就行了
//    但不能使用2Sum那种HashMap的解法
//      会有重复结果出现
//      就算使用 TreeSet 来去除重复也不行
//      会 TLE（Time Limit Exceeded）
//    既然另外两个数不能使用HashMap来找，如何能更有效的定位呢
//      肯定不希望遍历所有两个数的组合
//    如果数组是有序的
//      就可以用双指针
//      以线性时间复杂度来遍历所有满足题意的两个数组合

//    解法（排序+双指针法）
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        if (nums.length == 0) return res;
        if( nums[nums.length-1] < 0 || nums[0] > 0 ) return res;
        for (int k = 0 ; k < nums.length - 2; ++k){
            if(nums[k] > 0) break;
            if(k > 0 && nums[k] == nums[k-1]  ) continue;
            int target = 0 - nums[k];
            int i = k + 1,j = nums.length - 1;
            while(i < j){
                if (nums[i] + nums[j] == target ){
                    List<Integer> a = new ArrayList<Integer>();
                    a.add(nums[k]);
                    a.add(nums[i]);
                    a.add(nums[j]);
                    res.add(a);
                    while (i < j && nums[i] == nums[i+1]) ++i;
                    while (i < j && nums[j] == nums[j-1]) --j;
                    ++i;--j;
                }
                else if( nums[i] + nums[j] < target) i++;
                else j--;
            }
        }
        return res;
    }
}
