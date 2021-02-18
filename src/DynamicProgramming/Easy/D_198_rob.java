package DynamicProgramming.Easy;

//你是一个专业的小偷，计划偷窃沿街的房屋。
//每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统
//如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
//Offer: 给定一个代表每个房屋存放金额的非负整数数组
//Target: 计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
public class D_198_rob {
//    解法：动态规划
//    动态规划方程：dp[n] = MAX( dp[n-1], dp[n-2] + num )
//    由于不可以在相邻的房屋闯入，所以在当前位置 n 房屋可盗窃的最大值
//    要么就是 n-1 房屋可盗窃的最大值，要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值，二者之间取最大值
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0)
            return 0;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2; i <= len; i++) {
            //dp[i]代表的是已经偷完前面i-1家后，偷得的所有钱
            //nums[i-1]代表现在偷第i-1家
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }
}
