package DynamicProgramming.Middle;

import java.util.HashMap;

//Offer: 一个整数 n
//Target: 以 1 ... n 为节点组成的二叉搜索树有多少种？
//        和 95 题一样，只不过这道题不需要输出所有的树，只需要输出所有可能的二分查找树的数量
//        所以完全按照 95 题思路写
public class D37_96_numTrees {
//    解法1:递归
//    下边是 95 题的分析:
//    我们可以利用一下查找二叉树的性质。左子树的所有值小于根节点，右子树的所有值大于根节点。
//    所以如果求 1...n 的所有可能。
//    我们只需要把 1 作为根节点，[ ] 空作为左子树，[ 2 ... n ] 的所有可能作为右子树。
//            2 作为根节点，[ 1 ] 作为左子树，[ 3...n ] 的所有可能作为右子树。
//            3 作为根节点，[ 1 2 ] 的所有可能作为左子树，[ 4 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
//            4 作为根节点，[ 1 2 3 ] 的所有可能作为左子树，[ 5 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
//            ...
//    n 作为根节点，[ 1... n ] 的所有可能作为左子树，[ ] 作为右子树。
//    至于，[ 2 ... n ] 的所有可能以及 [ 4 ... n ] 以及其他情况的所有可能
//    可以利用上边的方法，把每个数字作为根节点，然后把所有可能的左子树和右子树组合起来即可。
//    如果只有一个数字，那么所有可能就是一种情况，把该数字作为一棵树。而如果是 [ ]，那就返回 null。
//    对于这道题，我们会更简单些，只需要返回树的数量即可。求当前根的数量，只需要左子树的数量乘上右子树。
    public int numTrees1_1(int n) {
        if (n == 0) {
            return 0;
        }
        return getAns(1, n);

    }
    private int getAns(int start, int end) {
        int ans = 0;
        //此时没有数字，只有一个数字,返回 1
        //因为求当前根的数量，要左子树的数量乘上右子树
        //所以当start > end时，得返回1，不能返回0（乘数得为 1）
        if (start >= end) {
            return 1;
        }
        //尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左子树
            int leftTreesNum = getAns(start, i - 1);
            //得到所有可能的右子树
            int rightTreesNum  = getAns(i + 1, end);
            //左子树右子树两两组合
            ans+=leftTreesNum * rightTreesNum;
        }
        return ans;
    }

//    受到这里的启发，我们甚至可以改写的更简单些
//    因为 95 题要把每颗树返回，所有传的参数是 start 和 end
//    这里的话，我们只关心数量，所以不需要具体的范围，而是传树的节点的数量即可。
    public int numTrees1_2(int n) {
        if (n == 0) {
            return 0;
        }
        return getAns(n);

    }

    private int getAns(int n) {
        int ans = 0;
        //此时没有数字或者只有一个数字,返回 1
        if (n==0 ||n==1) {
            return 1;
        }
        //尝试每个数字作为根节点
        for (int i = 1; i <= n; i++) {
            //得到所有可能的左子树
            // i - 1 代表左子树节点的数量
            int leftTreesNum = getAns(i-1);
            //得到所有可能的右子树
            //n - i 代表左子树节点的数量
            int rightTreesNum  = getAns(n-i);
            //左子树右子树两两组合
            ans+=leftTreesNum * rightTreesNum;
        }
        return ans;
    }

//    然后，由于递归的分叉，所以会导致很多重复解的计算
//    所以使用 memoization 技术，把递归过程中求出的解保存起来，第二次需要的时候直接拿即可
    public int numTrees1_3(int n) {
        if (n == 0) {
            return 0;
        }
        HashMap<Integer,Integer> memoization = new HashMap<>();
        return getAns(n,memoization);

    }

    private int getAns(int n, HashMap<Integer,Integer> memoization) {
        if(memoization.containsKey(n)){
            return memoization.get(n);
        }
        int ans = 0;
        //此时没有数字，只有一个数字,返回 1
        if (n==0 ||n==1) {
            return 1;
        }
        //尝试每个数字作为根节点
        for (int i = 1; i <= n; i++) {
            //得到所有可能的左子树
            int leftTreesNum = getAns(i-1,memoization);
            //得到所有可能的右子树
            int rightTreesNum  = getAns(n-i,memoization);
            //左子树右子树两两组合
            ans+=leftTreesNum * rightTreesNum;
        }
        memoization.put(n, ans);
        return ans;
    }


//    解法2：动态规划
//    直接利用95题解法三的思路，讲解比较长就不贴过来了，可以过去看一下。
//    或者直接从这里的解法一的思路考虑，因为递归是从顶层往下走，压栈压栈压栈，到了长度是 0 或者是 1 就出栈出栈出栈
//    我们可以利用动态规划的思想，直接从底部往上走。求出长度是 0，长度是 1，长度是 2....长度是 n 的解
//    用一个数组 dp 把这些结果全部保存起来
    public int numTrees2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (n == 0) {
            return 0;
        }
        // 长度为 1 到 n
        for (int len = 1; len <= n; len++) {
            // 将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len; root++) {
                int left = root - 1; // 左子树的长度
                int right = len - root; // 右子树的长度
                dp[len] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }

//    这里还有优化的空间
//    利用对称性，可以使得循环减少一些。
//        n 是偶数的时候
//            1 2 | 3 4 ，for 循环中我们以每个数字为根求出每个的解
//            我们其实可以只求一半，根据对称性我们可以知道 1 和 4，2 和 3 求出的解分别是相等的
//        n 是奇数的时候
//            1 2 | 3 | 4 5，和偶数同理，只求一半，此外最中间的 3 的解也要加上
    public int numTrees6(int n) {
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        // 长度为 1 到 n
        for (int len = 2; len <= n; len++) {
            // 将不同的数字作为根节点，只需要考虑到 len
            for (int root = 1; root <= len / 2; root++) {
                int left = root - 1; // 左子树的长度
                int right = len - root; // 右子树的长度
                dp[len] += dp[left] * dp[right];
            }
            dp[len] *= 2;// 利用对称性乘 2
            // 考虑奇数的情况
            if ((len & 1) == 1) {
                int root = (len >> 1) + 1;
                int left = root - 1; // 左子树的长度
                int right = len - root; // 右子树的长度
                dp[len] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }


//    解法3：公式法
//    利用的是卡塔兰数列的通项公式(具体看笔记 96. 不同的二叉搜索树)
    int numTrees3(int n) {
        long ans = 1, i;
        for (i = 1; i <= n; i++)
            ans = ans * (i + n) / i;
        return (int) (ans / i);
    }
//    总: 上道题会了以后，这道题很好写。解法二中利用对称的优化，解法三的公式太强了
}
