package DynamicProgramming.Easy;

//一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
//在每次预约服务之间要有休息时间，因此她不能接受相邻的预约
//Offer: 给定一个预约请求序列
//Target: 替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数
//注意：本题相对原题稍作改动

//示例 1：
//输入： [1,2,3,1]
//输出： 4
//解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
public class D36_massage {
//    递推方程：dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
//    空间 O(n)写法：
    public int massage1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }


//    空间O(1)写法：
    public int massage2(int[] nums) {
        int a = 0, b = 0, c = 0;
        for (int i = 0; i < nums.length; i++) {
            c = Math.max(b, a + nums[i]);
            a = b;
            b = c;
        }
        //return c也是可以的
        return b;
    }
}
