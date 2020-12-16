package Array.Easy;

import java.util.HashMap;

//Offer：一个数组，代表每阶楼梯消耗的体力值
//Target：
// 要求找到达到楼层顶部的最低花费是多少
// 在开始时，可以选择从索引为 0 或 1 的元素作为初始阶梯
// 每次可以选择继续爬一个阶梯或者爬两个阶梯
public class D16_746_MinCostClimbingStairs {
//    为了看懂题意
//    （在首尾都加一个0，分别代表地面和楼顶）
//    选择从索引0还是1出发，可走1或者2步；
//    如离开当前索引楼梯到达下一楼梯就消耗当前索引的体力值
//    对照例子再过一遍就懂了

//    思路1：动态规划 Dynamic Programming
//        定义一个一维的dp数组，dp[i]表示爬到第i层的最小 cost
//        来思考一下如何才能到第i层
//        只有两种可能性
//          从第 i-2 层上直接跳上来
//          或从第 i-1 层上跳上来
//        所以 dp[i] 只和前两层有关系
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i < n + 1; ++i) {
            dp[i] = Math.min(dp[i- 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n];
    }

    //类似 minCostClimbingStairs1()
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = cost[0]; dp[1] = cost[1];
        for (int i = 2; i < n; ++i) {
            dp[i] = cost[i] + Math.min(dp[i- 1], dp[i - 2]);
        }
        return Math.min(dp[n - 1], dp[n - 2]);
    }


//    优化空间复杂度：
//    通过前面的分析可以发现
//    当前的 dp 值仅仅依赖前面两个的值
//    所以不必把整个 dp 数组都记录下来
//        只需用两个变量a和b来记录前两个值
//        然后不停的用新得到的值来覆盖它们即可
    public int minCostClimbingStairs3(int[] cost) {
        int a = 0, b = 0;
        for (int num : cost) {
            int t = Math.min(a, b) + num;
            a = b;
            b = t;
        }
        return Math.min(a, b);
    }

//    递归：
//    需要优化计算量
//    所以用 HashMap 来保存已经算过了台阶
//    用的还是 dp 的思想
    public int minCostClimbingStairs4(int[] cost) {
        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return helper(cost, cost.length, memo);
    }

    public int helper(int[] cost, int i, java.util.HashMap<Integer, Integer> memo) {
        if (memo.get(i) != null) return memo.get(i);
        if (0 <= i && i <= 1) {
            memo.put(i,cost[i]);
            return cost[i];
        }
        int tmp = (i == cost.length ? 0 : cost[i]) + Math.min(helper(cost, i - 1, memo), helper(cost, i - 2, memo));
        memo.put(i,tmp);
        return tmp;
    }
}
