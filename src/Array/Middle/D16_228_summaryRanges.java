package Array.Middle;

//Offer：一个无重复元素的有序整数数组
//Target：返回数组区间范围的汇总

import java.util.ArrayList;
import java.util.List;

//示例 :
//输入: [0,1,2,4,5,7]
//输出: ["0->2","4->5","7"]
//解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间
public class D16_228_summaryRanges {
//    解法：双指针
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; j++) {
            // 结束了或者不连续则输出
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                result.add(String.valueOf(nums[i]) + (i == j ? "" : "->" + String.valueOf(nums[j])));
                i = j + 1;
            }
        }
        return result;
    }
}
