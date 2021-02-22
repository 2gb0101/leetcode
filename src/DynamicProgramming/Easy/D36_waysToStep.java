package DynamicProgramming.Easy;

//三步问题
//有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶
//实现一种方法，计算小孩有多少种上楼梯的方式
//结果可能很大，你需要对结果模1000000007
public class D36_waysToStep {
//    解法：动态规划
//    n层楼梯： f(n) = f(n-3) + f(n-2) + f(n-1);
//    取余公式：
//       (a + b + c)%p = ((a%p + b%p)%p + c%p)%p
    final static int mod = 1000000007;
    public static int waysToStep(int n) {
        switch (n){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            default:
                break;
        }
        int[] result = new int[n];
        result[0] = 1; result[1] = 2; result[2] = 4;
        for (int i = 3; i < n; i++){
            result[i] = ((result[i-3]%mod + result[i-2]%mod)%mod + result[i-1]%mod)%mod;
        }
        return result[n-1];
    }
}
