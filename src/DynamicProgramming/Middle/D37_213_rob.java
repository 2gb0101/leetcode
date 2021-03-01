package DynamicProgramming.Middle;

import java.util.Arrays;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的
//同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
//Offer: 给定一个代表每个房屋存放金额的非负整数数组
//Target: 计算你在不触动警报装置的情况下，能够偷窃到的最高金额
public class D37_213_rob {
//    解法：动态规划
//    环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃
//    因此问题变成：两个单排排列房间子问题
//        在不偷窃第一个房子的情况下，即 nums[1:]，最大金额是 p1
//        在不偷窃最后一个房子的情况下，即 nums[:n-1]，最大金额是 p2
//        综合偷窃最大金额为max(p1,p2)
//
//    接着解决单排排列房间问题
//    状态定义
//        设动态规划列表 dp，dp[i] 代表前 i 个房子在满足条件下的能偷窃到的最高金额

//    转移方程
//        设： 有 n 个房子，前 n 间能偷窃到的最高金额是 dp[n]，前 n-1 间能偷窃到的最高金额是 dp[n−1]
//        此时向这些房子后加一间房，此房间价值为 num
//        加一间房间后，由于不能抢相邻的房子，意味着抢第 n+1 间就不能抢第 n 间
//        那么前 n+1 间房能偷取到的最高金额 dp[n+1]，一定是以下两种情况的 较大值
//            不抢第 n+1 个房间：因此等于前 n 个房子的最高金额，即 dp[n+1] = dp[n]
//            抢第 n+1 个房间：此时不能抢第 n 个房间，因此等于前 n−1 个房子的最高金额加上当前房间价值，即 dp[n+1] = dp[n-1] + num
//        发现：在前 n 间的最高金额 dp[n] 情况下，第 n 间不一定被偷了
//            假设第 n 间没有被偷
//                那 n+1 间的最大值应该也可能是 dp[n+1] = dp[n] + num
//                其实这种情况可以省略，因为假设第 n 间没有被偷，此时 dp[n] = dp[n-1]
//                此时 dp[n+1] = dp[n] + num = dp[n-1] + num，即可以将 两种情况合并为一种情况
//            假设第 n 间被偷，那么 dp[n+1] = dp[n] + num 不可取，因为偷了第 n 间就不能偷第 n+1 间
//        最终的转移方程：dp[n+1] = max(dp[n],dp[n-1]+num)

//    初始状态
//        前 0 间房子的最大偷窃价值为 0 ，即 dp[0] = 0

//    返回值
//        返回 dp 列表最后一个元素值
//        即所有房间的最大偷窃价值

//    简化空间复杂度
//        发现 dp[n] 只与 dp[n−1] 和 dp[n−2] 有关系
//        因此可以设两个变量 cur和 pre 交替记录
//        将空间复杂度降到 O(1)

//    复杂度分析
//    时间复杂度 O(N)：两次遍历 nums 需要线性时间
//    空间复杂度 O(1)：cur和 pre 使用常数大小的额外空间
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}
