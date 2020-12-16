package Array.Easy;

//Offer：给定常数 N
//Target：计算斐波那契数 F(N)
//        F(0) = 0,   F(1) = 1
//        F(N) = F(N - 1) + F(N - 2), for N > 1
public class D11_509_FibonacciNumber {
//    题目没让返回整个数列，而是直接让返回位置为N的数字
//    那么还是要构建整个斐波那契数组，才能知道位置N上的数字
//    像这种有规律有 pattern 的数组，最简单的方法就是使用递归

//    递归法：
//    先把不合规律的前两个数字处理了
//    然后直接对 N-1 和 N-2 调用递归

//    缺点：
//    这里使用递归法会造成大量重复计算，导致时间复杂度为O(2^n)。
//    所以考虑使用动态规划消除重复计算
    public int fib1(int N) {
        if (N <= 1) return N;
        return fib1(N - 1) + fib1(N - 2);
    }

//    动态规划 Dynamic Programming：

//    数组法：可用数组保存计算结果
//    时间复杂度优化至O(n)
//    则空间复杂度为O(n)
    public int fib2(int N) {
        //首先要先记得处理N=0和N=1的特殊情况
        if(N == 0) return 0;
        else if(N == 1) return 1;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N];
    }

//    两个变量法：
//    进一步简化：只使用两个变量保存结果
//    由于当前数字只跟前两个数字有关：所以不需要保存整个数组
//    只需要保存前两个数字就行了
//    空间复杂度为O(1)
    public int fib3_1(int N) {
        if (N <= 1) return N;
        int a = 0, b = 1;
        for (int i = 2; i <= N; ++i) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

//    或者
    public int fib3_2(int N) {
        //可以理解成，cur是斐波那契中较小的加数，next是斐波那契中较大的加数
        int curr = 0, next = 1;
        while(N-- > 0) {
            //举例：N=2时
            //next = next + curr 等价于 F(2) = F(1) + F(0)
            //这一行是为了递增的计算F(N)
            next = next + curr;
            //curr = next - curr 等价于 F(1) = F(2) - F(0)
            //这一行是为了递增的计算F(N) = F(N-1) + F(N-2)中的F(N-2)
            curr = next - curr;
            //以此类推
        }
        return curr;
    }
}
