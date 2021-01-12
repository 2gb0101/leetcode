package Array.Hard;

public class D7_689_maxSumOfThreeSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        //第一维代表状态
        //	0代表已经有一个长度为k的子数组
        //	1代表有两个长度为k的子数组
        //	2代表有三个长度为k的子数组
        //	我们的目标是求dp[2][N-k]
        //第二维代表索引，dp[i][j]代表的是前i个子数组的前缀和，j为第i个子数组的最后一个数的下标
        int[][] dp = new int[3][nums.length];
        int[] cummulative = new int[nums.length]; //记录前缀和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            cummulative[i] = sum;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (j < (i + 1) * k - 1) {
                    dp[i][j] = 0;
                } else {
                    //状态0可以向状态0或者1转移;
                    //状态1可以向状态1或者2转移;
                    //状态2只能向状态2转移
                    //计算当前状态：dp[i][j]
                    //	根据前一层的状态计算当前可能的结果：rangeSum(cummulative, j - k + 1, j) + dp[i - 1][j - k]
                    //	前一个结果如果比当前可能的结果要好：dp[i][j - 1]
                    if (i == 0) {
                        // 易错点: 当k=1时，即i=0，边界条件需要处理一下

                        // 这里的转移方程其实就是：dp[i][j] = Math.max(dp[i][j - 1], rangeSum(j - k + 1, j))
                        // j > 0 ? dp[i][j - 1] : 0 这个判断是为了解决j - 1>0的问题
                        dp[i][j] = Math.max(j > 0 ? dp[i][j - 1] : 0, rangeSum(cummulative, j - k + 1, j));
                    } else {
                        dp[i][j] = Math.max(j > 0 ? dp[i][j - 1]: 0, rangeSum(cummulative, j - k + 1, j) + dp[i - 1][j - k]);
                    }
                }

            }
        }

        int[] ans = new int[3];
        int length = dp[2].length - 1;
        for (int i = 2; i >= 0; i--) {
            int[] row = dp[i];
            for (int j = length - 1; j >= 0; j--) {
                if (row[j] != row[length]) {
                    ans[i] = (j - k + 1) + 1; //计算子数组开头的下标，见下图解
                    //再计算前一层之前，先缩短要搜索的距离
                    //因为前一层的子数组开头的下标，绝对不会在j - k + 1之后的位置了
                    length = j - k + 1;
                    break;
                }
            }
        }
        return ans;
    }

    //利用前缀和，计算子数组的和
    private int rangeSum(int[] cummulative, int left, int right) {
        if (left == 0) {
            return cummulative[right];
        } else {
            return cummulative[right] - cummulative[left - 1];
        }
    }

}
