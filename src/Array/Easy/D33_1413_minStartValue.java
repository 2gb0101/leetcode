package Array.Easy;

//给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目
//对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果
//注意，允许有多个孩子同时拥有 最多 的糖果数目

import java.util.ArrayList;
import java.util.List;

//输入：candies = [2,3,5,1,3], extraCandies = 3
//输出：[true,true,true,false,true]
public class D33_1413_minStartValue {
//    首先找到每个孩子糖果数的最大值，然后每个孩子增加额外的糖果
//    如果增加后的糖果数大于等于最大值则为true，否则false
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int c : candies) {
            max = Math.max(max, c);
        }

        List<Boolean> ans = new ArrayList<>();
        for (int c : candies) {
            if (c + extraCandies >= max) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }
        return ans;
    }
}
