package Array.Easy;

//Offer：一个整数 n
//Target：
//    返回 任意 一个由 n 个 各不相同 的整数组成的数组
//    且这 n 个数相加和为 0

//示例 1：
//输入：n = 5
//输出：[-7,-1,1,3,4]
//解释：这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]
public class D30_1304_sumZero {
//    解法1：正负数以0为中心对称
    public int[] sumZero1(int n) {
        int[] ans = new int[n];
        int index = 0;
        for (int i = 1; i <= n / 2; i++) {
            ans[index++] = -i;
            ans[index++] = i;
        }
        return ans;
    }

//    解法2：最后一个数等于前面数的和的相反数
    public int[] sumZero2(int n) {
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i <= n-2; i++) {
            arr[i] = i;
            sum += i;
        }
        arr[n-1] = -sum;
        return arr;
    }
}
