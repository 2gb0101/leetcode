package Array.Hard;

//Offer: 一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格
//Target: 设计一个算法来计算你所能获取的最大利润，你最多可以完成 两笔 交易
//        注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
public class D4_123_maxProfit {
//    解法一比较常规，但是这个动态规划难在了我们考虑了两个变量，相比于之前的动态规划不容易想到
//
//    解法1：动态规划
//    当达到了一个高点时不一定要卖出，所以需要考虑的情况就很多了，那只能朝着动态规划思路想了
//    动态规划关键：数组定义和状态转移方程
//    按最简单的动态规划的思路想
//        用 dp[i]表示前i天的最高收益
//        那么 dp[i+1] 怎么根据 dp[i] 求出来呢？发现并不能求出来
//    注意到题目是求那么多天最多交易两次的最高收益，还有一个最多交易次数的变量，我们把它加到数组中再试一试
//        用 dp[i][k] 表示前i天最多交易k次的最高收益，那么 dp[i][k] 怎么通过之前的解求出来呢？
//        第 i 天可以什么都不操作
//            今天的最高收益就等于昨天的最高收益
//            dp[i][k] = dp[i-1][k]
//        为了获得更大收益我们第 i 天也可以选择卖出
//            既然选择卖出，那么在0到 i-1 天就要选择一天买入多选择了一次买入，那在买入之前已经进行了 k-1 次买卖
//                在第 0 天买入，收益就是 prices[i] - prices[0]
//                在第 1 天买入，收益就是 prices[i] - prices[1] + dp[0][k-1]，多加了前一天的最大收益
//                在第 2 天买入，收益就是 prices[i] - prices[2] + dp[1][k-1]，多加了前一天的最大收益
//                ...
//                在第 j 天买入，收益就是 prices[i] - prices[j] + dp[j-1][k-1]，多加了前一天的最大收益
//                上边的每一种可能选择一个最大的，然后与第i天什么都不操作比较，就是dp[i][k]的值了

//    当然上边的推导已经可以写代码了，但为了最后的代码更加简洁（写完代码后发现的），我们可以再换一下状态转移方程
//        真的只是为了简洁，时间复杂度和空间复杂度上不会有影响
//    为了获得更大收益我们第 i 天也可以选择卖出，既然选择卖出，那么在0到 i-1 天就要选择一天买入
//    我们也可以选择0到i天中选择一天买入，因为第 i 天买入，第 i 天卖出对最后的收益是没有影响的
//    在第 j 天买入，收益就是 prices[i] - prices[j] + dp[j-1][k-1]
//    我们多加了前一天的最大收益，我们也可以改成加当前天的最大收益
//    在第 j 天买入，收益就是 prices[i] - prices[j] + dp[j][k-1]
//    不严谨的想一下
//        如果第 j 天就是最后我们要选择的买入点，它使得最后的收益最高，dp[j][k-1] 和 dp[j-1][k-1] 一定是相等的
//        因为第 j 天一定是一个低点而第 j - 1 天是个高点
//        第 j 天为了得到更高收益肯定选择不操作，所以和第 j - 1 天的收益是一样的
//        所以改了状态转移方程，最后求出的最高解还是一致的
//    综上，最后的状态转移方程就是
//        dp[i][k] = Max(dp[i-1][k],(prices[i] - prices[0] + dp[0][k-1]),
//                       (prices[i] - prices[1] + dp[1][k-1])
//                    ...(prices[i] - prices[i] + dp[i][k-1]))
//    也就是
//        dp[i][k] = Max(dp[i-1][k],prices[i] - prices[j] + dp[j][k-1])，j 取 0 ~ i
//    而 prices[i] - prices[j] + dp[j][k-1] 也可以看做， prices[i] - (prices[j] - dp[j][k-1])
//        为了求这个表达式的最大值，我们可以找prices[j] - dp[j][k-1]的最小值
//        而初始条件对于k 等于 0 的情况，收益就是 0 了
//        还有前 0 天的最大收益也是 0 ，也就是dp[0][k]是 0 由于下标是从0开始的，这里的前0天其实就是第一天
//        因为初始条件的结果都是0，数组初始化后就是 0 ，所以不需要特殊处理
    public int maxProfit1_1(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[][] dp = new int[prices.length][K + 1];
        for (int k = 1; k <= K; k++) { //这个遍历顺序可以画图（参见笔记【123. 买卖股票的最佳时机 III】）
            for (int i = 1; i < prices.length; i++) {
                int min = Integer.MAX_VALUE;
                //找出第 0 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值
                for (int buy = 0; buy <= i; buy++) {
                    min = Math.min(prices[buy] - dp[buy][k - 1], min);
                }
                //比较不操作和选择一天买入的哪个值更大
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[prices.length - 1][K];
    }

//    找第 j 天prices[buy] - dp[buy][k - 1] 的最小值的时候
//    我们考虑了 prices[0] - dp[0][k - 1] 、 prices[1] - dp[1][k - 1] 、 prices[2] - dp[2][k - 1] ...，
//    找第 j + 1 天prices[buy] - dp[buy][k - 1] 的最小值的时候
//    我们又会从头考虑 prices[0] - dp[0][k - 1] 、 prices[1] - dp[1][k - 1] 、 prices[2] - dp[2][k - 1] ...
//    其实没必要每次从头考虑，我们只需要把之前的结果保存起来，然后再和新加入的 prices[j+1] - dp[j+1][k - 1] 比较就可以了
    public int maxProfit1_2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[][] dp = new int[prices.length][K + 1];
        for (int k = 1; k <= K; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                //找出第 1 天到第 i 天 prices[buy] - dp[buy][k - 1] 的最小值 
                min = Math.min(prices[i] - dp[i][k - 1], min);
                //比较不操作和选择一天买入的哪个值更大
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min);
            }
        }
        return dp[prices.length - 1][K];
    }

//    根据代码，我们是固定 k 然后一列一列更新 dp而更新当前列只需要前一列的信息
//    所以不需要二维数组，只需要一个一维数组
//    但是注意到最外层的 for 循环是一个常数次，所以我们可以把两层循环内外颠倒下，可以更好的进行空间复杂度的优化
    public int maxProfit1_3(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[][] dp = new int[prices.length][K + 1];
        int min[] = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            min[i] = prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= K; k++) {
                min[k] = Math.min(prices[i] - dp[i][k - 1], min[k]);
                dp[i][k] = Math.max(dp[i - 1][k], prices[i] - min[k]);
            }
        }
        return dp[prices.length - 1][K];
    }

//    再结合图看，此时我们就是一行一行的更新了，对于每一列都有一个 min
//    所以我们多了 min 数组
//    现在让我们将二维数组 dp 改成一维数组
    public int maxProfit1_4(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int K = 2;
        int[] dp = new int[K + 1];
        int min[] = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            min[i] = prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int k = 1; k <= K; k++) {
                min[k] = Math.min(prices[i] - dp[k - 1], min[k]);
                dp[k] = Math.max(dp[k], prices[i] - min[k]);
            }
        }
        return dp[K];
    }

//  由于 K 是一个常数，所以我们的 min 数组和 dp 数组都可以分别当成两个变量
    public int maxProfit1_5(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int dp1 = 0;
        int dp2 = 0;
        //由于 K 是一个常数，所以我们的 min 数组和 dp 数组都可以分别当成两个变量
        int min1 = prices[0];
        int min2 = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min1 = Math.min(prices[i] - 0, min1);
            dp1 = Math.max(dp1, prices[i] - min1);

            min2 = Math.min(prices[i] - dp1, min2);
            dp2 = Math.max(dp2, prices[i] - min2);
        }
        return dp2;
    }
//    如果结合一步一步的优化，最后这个代码也就很好的能解释通了


//    解法2：状态机
//    再分享个利用状态机的解法，虽然不容易想到，但真的太强了，上次用状态机还是 65 题
//    每天我们其实是有四个状态，买入当前价格的股票，以当前价格的股票卖出，第二次买入股票，第二次卖出股票
//        s0代表初始状态，初始时钱是 0
//        s1代表第一次买入后当前的钱
//        s2代表第一次卖出后当前的前
//        s3代表第二次买入后当前的钱
//        s4代表第二次卖出后当前的钱
//    然后我们只需要更新每天的这四个状态即可
    int maxProfit2(int[] prices) {
        if(prices.length == 0)
            return 0;
        //进行初始化，第一天 s1 将股票买入，其他状态全部初始化为最小值
        int s1=-prices[0],s2=Integer.MIN_VALUE,
                s3=Integer.MIN_VALUE,s4=Integer.MIN_VALUE;

        for(int i=1;i < prices.length;++i) {
            s1 = Math.max(s1, -prices[i]); //买入价格更低的股
            s2 = Math.max(s2, s1+prices[i]); //卖出当前股，或者不操作
            s3 = Math.max(s3, s2-prices[i]); //第二次买入，或者不操作
            s4 = Math.max(s4, s3+prices[i]); //第二次卖出，或者不操作
        }
        return Math.max(0,s4);
    }
}
