package Array.Hard;

import java.util.Arrays;
import java.util.Collections;

//Offer：一个整数数组nums
//Target：请你找到可行的最大数组值
//「数组值」定义：所有满足0 <= i < nums.length-1的|nums[i]-nums[i+1]|的和
//你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作一次
public class D10_1330_maxValueAfterReverse {
//    解法：从数轴的角度解
//    翻转子数组，子数组内部计算数组值完全相同，唯一存在的差异就在子数组的两个端点。
//    这时分两种情况：
//    其中一个端点就是nums的端点：这时线性扫一遍其余点即可；
//    两个端点均不是nums的端点：将四个点(子数组的端点L、R和L-1、R+1)按大小标记在数轴上
//
//    很容易发现只有当[nums[L], nums[L-1]]和[nums[R], nums[R+1]] 不相交时，翻转[L, R]子数组才能为数组值增加2倍中间不相交的间距；
//    这时将每个(nums[i], nums[i+1])排好序，直接和”最大“值比即可；
//    两种情况都是O(n).
    public int maxValueAfterReverse(int[] nums) {
        int leng = nums.length;
        int f1,f2,f3,f4,g1,g2,g3,g4,sum,max,left0,right0;
        sum = max = 0;
        left0 = right0 = f1 = f2 = f3 = f4 = g1 = g2 = g3 = g4 = Integer.MIN_VALUE;

        //left = 0
        for(int i = 0;i < leng;i ++){
            if(i < leng - 1){
                left0 = Math.max(left0,
                        Math.abs(nums[0] - nums[i + 1]) - Math.abs(nums[i] - nums[i + 1]));//见图解
                sum += Math.abs(nums[i] - nums[i + 1]);
                int r1v = nums[i + 1];
                int absR = Math.abs(nums[i] - r1v);
                g1 = Math.max(g1,0 - nums[i] - r1v - absR);
                g2 = Math.max(g2,0 - nums[i] + r1v - absR);
                g3 = Math.max(g3,nums[i] - r1v - absR);
                g4 = Math.max(g4,nums[i] + r1v - absR);
            }

            if(i > 0){
                right0 = Math.max(right0,
                        Math.abs(nums[i - 1] - nums[leng - 1]) - Math.abs(nums[i] - nums[i - 1]));

                int l1v = nums[i - 1];

                int absL = Math.abs(l1v - nums[i]);

                f1 = Math.max(f1,l1v + nums[i] - absL);
                f2 = Math.max(f2,l1v - nums[i] - absL);
                f3 = Math.max(f3,nums[i] - l1v - absL);
                f4 = Math.max(f4,0 - l1v - nums[i] - absL);
            }
        }

        max = Collections.max(Arrays.asList(f1 + g1,f2 + g2,f3 + g3,f4 + g4,left0,right0));
        return sum + max;
    }
}
