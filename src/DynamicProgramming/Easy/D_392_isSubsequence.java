package DynamicProgramming.Easy;

//Offer: 给定字符串 s 和 t
//Target: 判断 s 是否为 t 的子序列
//你可以认为 s 和 t 中仅包含英文小写字母
//字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）
//字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串
//（例如，"ace"是"abcde"的一个子序列，而"aec"不是）

//后续挑战 :
//    如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列
//    在这种情况下，你会怎样改变代码？

public class D_392_isSubsequence {
//    解法1：DP
//    该方法耗时长，只是练练DP而已
//
//    思路：
//    状态：dp[i][j]为s的从头开始到i的子字符串是否为t从头开始到j的子字符串的子序列
//    状态转移公式：
//        当char[i]==char[j]时，则字符i一定是j的子序列
//              如果0~i-1子字符串是0~j-1子字符串的子序列，则dp[i][j]=true
//              所以dp[i][j] = dp[i-1][j-1]；
//        当char[i]!=char[i]时
//              即判断当前0~i子字符串是否是0~j-1的子字符串的子序列，即dp[i][j] = dp[i][j - 1]
//              如ab，eabc，虽然s的最后一个字符和t中最后一个字符不相等
//              但是因为ab是eab的子序列，所以ab也是eabc的子序列
//    初始化：
//          空字符串一定是t的子字符串的子序列，所以dp[0][j]=true
//    结果：
//          返回dp[sLen][tLen]
    public boolean isSubsequence1(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return false;
        if (sLen == 0) return true;
        boolean[][] dp = new boolean[sLen + 1][tLen + 1];
        //初始化
        for (int j = 0; j < tLen; j++) {
            dp[0][j] = true;
        }
        //dp
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[sLen][tLen];
    }


//    解法2：使用库函数indexOf()
//    java中非常好的一个方法
//    indexOf(char c,int m)意思是从第m位置开始寻找该索引，找到则返回该索引，否则返回-1
//    利用该特性我们通过对索引处理从而获得解
    public boolean isSubsequence2(String s, String t) {
        char[] arr = s.toCharArray();
        int j = -1;
        for(int i = 0;i<arr.length;i++) {
            j = t.indexOf(arr[i],j+1);
            if(j==-1) {
                return false;
            }
        }
        return true;
    }

//    解法3：双指针
//    如果是匹配一个较短字符串 s ，对于 s中每一个char 都优先匹配最开始遇到的，直接扫描一遍 t 即可
    public boolean isSubsequence3(String s, String t) {
        int i = 0;
        for (char ch : s.toCharArray()) {
            while (i < t.length() && t.charAt(i) != ch) i++;
            i++;
        }
        return i <= t.length() ? true : false;
    }


//    后续挑战
//    匹配一串字符需要 O(n) ，n 为 t 的长度
//    如果有大量输入的 S，称作 S1 , S2 , ... , Sk 其中 k >= 10 亿
//    你需要依次检查它们是否为 T 的子序列，这时候处理每一个子串都需要扫描一遍 T 是很费时的
//    在这种情况下，我们需要在匹配前对 T 做预处理
//        利用一个二维数组记录每个位置的下一个要匹配的字符的位置
//        这里的字符是'a' ~ 'z'，所以这个数组的大小是 dp[n][26]，n 为 T 的长度
//        那么每处理一个子串只需要扫描一遍 Si 即可，因为在数组的帮助下我们对 T 是“跳跃”扫描的
//        比如下面匹配 "ada" 的例子，只需要“跳跃”三次
    public boolean isSubsequence4(String s, String t) {
        // 预处理；
        t = " " + t; // 开头加一个空字符作为匹配入口
        int n = t.length();
        int[][] dp = new int[n][26]; // 记录每个位置的下一个ch的位置
        for (char ch = 0; ch < 26; ch++) {
            int p = -1;
            for (int i = n - 1; i >= 0; i--) { // 从后往前记录dp
                dp[i][ch] = p;
                if (t.charAt(i) == ch + 'a') p = i;
            }
        }
        // 匹配
        int i = 0;
        for (char ch : s.toCharArray()) { // 跳跃遍历
            i = dp[i][ch - 'a'];
            if (i == -1) return false;
        }
        return true;
    }
}
