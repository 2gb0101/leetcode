package Array.Middle;

//Offer: 给两个整数数组 A 和 B
//Target: 返回两个数组中公共的、长度最长的子数组的长度
public class D22_718_findLength {
//    解法：动态规划
//    做动态规划的题目一定要把dp的含义弄懂
//        动态规划，其实就是一种暴力的搜索，只是它会记录着已经运算好的值，并且加以使用
//    在这里根据题目公共的、长度最长的子数组, 所以dp[i][j]含义是的子数组的长度
    public int findLength(int[] A, int[] B) {
        int i,b;
        int len = 0;
        int[][] dp = new int[A.length + 1][B.length + 1]; //建立一个二维表。
        for(i = 1; i <= A.length; i++){
            for(b = 1; b <= B.length; b++){
                if(A[i - 1] == B[b - 1]){//如果两个元素相等，就看他前面一个元素匹配的结果。
                    dp[i][b] = dp[i - 1][b - 1] + 1;
                }
                if(dp[i][b] > len){//这里是找最长得长度
                    len = dp[i][b];
                }
            }
        }
        return len;
    }
}
