package DynamicProgramming.Middle;

//给定一个字符串 s，找到 s 中最长的回文子串
//你可以假设 s 的最大长度为 1000

//示例 1：
//输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案
//
//示例 2：
//输入: "cbbd"
//输出: "bb"
public class D35_05_longestPalindrome {
//    说明：
//    以下解法中「暴力算法」是基础，「动态规划」必须掌握，「中心扩散」方法要会写；
//            「Manacher 算法」仅用于扩宽视野，绝大多数的算法面试中，面试官都不会要求写这个方法（除非面试者是竞赛选手）
//
//    Manacher 算法看下面的链接：
//    https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/

//    解法1：暴力匹配 （Brute Force）
//    根据回文子串的定义，枚举所有长度大于等于 2 的子串，依次判断它们是否是回文；
//    在具体实现时，可以只针对大于“当前得到的最长回文子串长度”的子串进行“回文验证”；
//    在记录最长回文子串的时候，可以只记录“当前子串的起始位置”和“子串长度”，不必做截取这一步我们放在后面的方法中实现

//    说明：
//      暴力解法时间复杂度高，但是思路清晰、编写简单
//      由于编写正确性的可能性很大，可以使用暴力匹配算法检验我们编写的其它算法是否正确
//      优化的解法在很多时候，是基于“暴力解法”，以空间换时间得到的，因此思考清楚暴力解法，分析其缺点，很多时候能为我们打开思路
    public String longestPalindrome1(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
//    复杂度分析：
//    时间复杂度：O(N^3)，这里 N 是字符串的长度，枚举字符串的左边界、右边界，然后继续验证子串是否是回文子串，这三种操作都与 N 相关；
//    空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关

//    解法2：动态规划
//    这道题比较烦人的是判断回文子串因此需要一种能够快速判断原字符串的所有子串是否是回文子串的方法，于是想到了「动态规划」
//    「动态规划」的一个关键的步骤是想清楚「状态如何转移」
//    事实上，「回文」天然具有「状态转移」性质
//    一个回文去掉两头以后，剩下的部分依然是回文（这里暂不讨论边界情况）；
//    依然从回文串的定义展开讨论：
//        如果一个字符串的头尾两个字符都不相等，那么这个字符串一定不是回文串；
//        如果一个字符串的头尾两个字符相等，才有必要继续判断下去
//            如果里面的子串是回文，整体就是回文串；
//            如果里面的子串不是回文串，整体就不是回文串
//        即：在头尾字符相等的情况下，里面子串的回文性质据定了整个子串的回文性质，这就是状态转移
//    因此可以把「状态」定义为原字符串的一个子串是否为回文子串

//    第 1 步：定义状态
//        dp[i][j] 表示子串 s[i..j] 是否为回文子串
//        这里子串 s[i..j] 定义为左闭右闭区间，可以取到 s[i] 和 s[j]
//
//    第 2 步：思考状态转移方程
//        在这一步分类讨论（根据头尾字符是否相等），根据上面的分析得到：
//        dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
//        说明：
//            「动态规划」事实上是在填一张二维表格，由于构成子串
//            因此 i 和 j 的关系是 i <= j
//            因此，只需要填这张表格对角线以上的部分
//        看到 dp[i + 1][j - 1] 就得考虑边界情况
//            边界条件是：表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2
//            即 j - 1 - (i + 1) + 1 < 2 ，整理得 j - i < 3
//            这个结论很显然：j - i < 3 等价于 j - i + 1 < 4
//            即当子串 s[i..j] 的长度等于 2 或者等于 3 的时候
//            其实只需要判断一下头尾两个字符是否相等就可以直接下结论了
//                如果子串 s[i + 1..j - 1] 只有 1 个字符，即去掉两头，剩下中间部分只有 1 个字符，显然是回文；
//                如果子串 s[i + 1..j - 1] 为空串，那么子串 s[i, j] 一定是回文子串
//                因此，在 s[i] == s[j] 成立和 j - i < 3 的前提下，直接可以下结论，dp[i][j] = true，否则才执行状态转移
//
//    第 3 步：考虑初始化
//        初始化的时候，单个字符一定是回文串
//            因此把对角线先初始化为 true
//            即 dp[i][i] = true
//        事实上，初始化的部分都可以省去因为只有一个字符的时候一定是回文，dp[i][i] 根本不会被其它状态值所参考
//
//    第 4 步：考虑输出
//        只要一得到 dp[i][j] = true，就记录子串的长度和起始位置
//        没有必要截取
//            这是因为截取字符串也要消耗性能
//            记录此时的回文子串的「起始位置」和「回文长度」即可
//
//    第 5 步：考虑优化空间
//        因为在填表的过程中，只参考了左下方的数值
//        事实上可以优化，但是增加了代码编写和理解的难度，丢失可读和可解释性在这里不优化空间
//        注意事项：总是先得到小子串的回文判定，然后大子串才能参考小子串的判断结果，即填表顺序很重要
    public String longestPalindrome2(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                // 上面这行改成下面这行for循环的写法也是对的，i 正着写、倒过来写都行，因为子串都有参考值
                //for (int i = j - 1; i >= 0; i--) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
//    「状态转移方程」其实可以写得更酷一点：借用 or 的短路功能，如果 j - i < 3 成立，其实后面就不用计算了
//    状态转移方程可以写成如下：
//    dp[i][j] = (s[i] == s[j]) and (j - i < 3 or dp[i + 1][j - 1])
//    写成代码即：dp[i][j] = charArray[i] == charArray[j] && (j - i < 3 || dp[i + 1][j - 1]);
//    代码虽然看起来短了一些，但是丢失了可读性，逻辑运算符混用
//    虽然加上了括号表示优先级，但如果没有前文铺垫，很难读懂是什么意思，不太推荐大家这么写

//    总结：
//    我们看到，用「动态规划」方法解决问题，有的时候并不是直接面向问题的
//    「动态规划」依然是「空间换时间」思想的体现，并且本身「动态规划」作为一种打表格法，就是在用「空间」换「时间」
//
//    关于「动态规划」方法执行时间慢的说明：
//    动态规划本质上还是「暴力解法」，因为需要枚举左右边界，有 O(N^2)这么多；
//    以下提供的「中心扩散法」枚举了所有可能的回文子串的中心，有 O(2N)这么多，不在一个级别上
//    上面采用了时间复杂度估算的表示，O(N^2)
//    表示最高项是 a*(N^2)的多项式
//    从这个问题我们也可以看出，虽然时间复杂度一样，但是实际执行时间有可能是有差距的
//    时间复杂度是一个估算，没有必要计算得很清楚，这是因为真正的运行时间还和很多因素有关：例如所运行机器的环境，测试用例等

//    解法3：中心扩散法
//    暴力法采用双指针两边夹，验证是否是回文子串
//    除了枚举字符串的左右边界以外，比较容易想到的是枚举可能出现的回文子串的“中心位置”
//        从“中心位置”尝试尽可能扩散出去，得到一个回文串
//        因此中心扩散法的思路是：遍历每一个索引，以这个索引为中心，利用“回文串”中心对称的特点，往两边扩散，看最多能扩散多远
//    枚举“中心位置”时间复杂度为 O(N)，从“中心位置”扩散得到“回文子串”的时间复杂度为 O(N)，因此时间复杂度可以降到 O(N^2)
//
//    在这里要注意一个细节：回文串在长度为奇数和偶数的时候，“回文中心”的形式是不一样的
//        奇数回文串的“中心”是一个具体的字符，例如：回文串 "aba" 的中心是字符 "b"；
//        偶数回文串的“中心”是位于中间的两个字符的“空隙”，例如：回文串串 "abba" 的中心是两个 "b" 中间的那个“空隙”
//    我们看一下一个字符串可能的回文子串的中心在哪里？
//    我们可以设计一个方法，兼容以上两种情况：
//    1、如果传入重合的索引编码，进行中心扩散，此时得到的回文子串的长度是奇数；
//    2、如果传入相邻的索引编码，进行中心扩散，此时得到的回文子串的长度是偶数
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }

    private String centerSpread(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
        return s.substring(i + 1, j);
    }
//    复杂度分析：
//    时间复杂度：O(N2)，理由已经叙述
//    空间复杂度：O(1)，只使用到常数个临时变量，与字符串长度无关

}
