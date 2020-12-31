package Array.Middle;

//如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
//n >= 3
//对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}

//Offer: 一个严格递增的正整数数组形成序列
//Target: 找到 A 中最长的斐波那契式的子序列的长度
//        如果一个不存在，返回  0

//说明：子序列
//        是从原序列 A 中派生出来的
//        它从 A 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序
//        例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列

import org.junit.Test;

//示例
//输入: [1,3,7,11,12,14,18]
//输出: 3
//解释:
//最长的斐波那契式子序列有：
//[1,11,12]，[3,11,14] 以及 [7,11,18]
public class D25_873_lenLongestFibSubseq {
//    解法：动态规划
//    一直想着的是“以某一个元素结尾的最长斐波那契子序列长度”
//    没想到它应该是“以某两个元素结尾的最长斐波那契序列长度”

//    设dp[i][j]表示以(A[i],A[j])结尾的最长斐波那契子序列的长度，初始化为2，因为斐波那契序列最短为3
//    状态转移方程：
//    dp[i][i+1]=dp[i-1][i]+1
//    返回最大的dp[i][j]
//    借助哈希表使时间复杂度O(n^2)
//    public int lenLongestFibSubseq(int[] A) {
//        int n = A.length;
//        // key代表A[i]，value代表i
//        HashMap<Integer,Integer> find_k=new HashMap<>();
//        for(int i=0;i<n;i++){
//            fin d_k.put(A[i],i);//A严格递增
//        }
//        int [][] dp_ij=new int[n][n];//以A[i],A[j]结尾的最长斐波那契子序列长度，即...A[i],A[j]
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                dp_ij[i][j] = 2;
//            }
//        }
//        int max_len=0;
//        for(int i=1;i<n-1;i++){
//            for(int j=i+1;j<n;j++){
//                Object k=find_k.get(A[j]-A[i]);
//                //A[j]-A[i] 等价于 A[i+1]-A[i]，k其实代表的就是i-1
//                //因为斐波那契子序列的定义就是A[i-1] + A[i] = A[i+1]
//                if(k!=null){
//                    if((int)k<i)
//                        dp_ij[i][j]=dp_ij[(int)k][i]+1;
//                }
//                max_len=Math.max(max_len,dp_ij[i][j]);
//
//            }
//        }
//        return max_len>2?max_len:0;
//
//    }

    @Test
    public void test(){
        String i = "01,02,03";
        i = i.replaceAll(",","','");
        System.out.println(i);
    }
}
