package Utils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Utils_GCD {
    /**
     * 求最大公约数 辗转相除法(欧几里德算法)
     * 用辗转相除法求几个数的最大公约数，可以先求出其中任意两个数的最大公约数，再求这个最大公约数与第三个数的最大公约数，依次求下去，直到最后一个数为止。
     * 最后所得的那个最大公约数，就是所有这些数的最大公约数。
     */

//    两个整数的最大公约数等于：其中较小的数和两数的差的最大公约数
//    个人解析：
//    若A、B有最大公约数K（A > B)
//    则：
//        A、B、（A - B）、A mod B（A / B的余数）
//        都是K的倍数
//        即余数（A - B）和 B 的最大公约数也是 K
//    由此递归
//        可知当 A mod B = 0
//        即 A 是 B 的倍数时
//        此时，B 即为 K 。
//    例子：
//    求（319，377）
//            ∵ 319÷377=0（余319）
//            ∴（319，377）=（377，319）
//            ∵ 377÷319=1（余58）
//            ∴（377，319）=（319，58）
//            ∵319÷58=5（余29）
//            ∴ （319，58）=（58，29）
//            ∵ 58÷29=2（余0）
//            ∴ （58，29）= 29
//            ∴（319，377）=29
//    补充：在测试中发现，319 % 377 = 319
    public static int GCD(int m, int n) {
        int result = 0;
        while (n != 0) {
            result = m % n;
            m = n;
            n = result;
        }
        return m;
    }


    /**
     * 质因数分解法：
     * 把每个数分别分解质因数，再把各数中的全部公有质因数提取出来连乘，所得的积就是这几个数的最大公约数。 (小学学的方法)
     */
    public static int PrimeGCD(int m, int n) {
        int result = 1;
        //getFactor()定义见下面
        Set<Integer> set1 = getFactor(m);
        //此时set1的值为{11，29，319}
        Set<Integer> set2 = getFactor(n);
        //此时set2的值为{377，13，29}
        // 取交集
        set1.retainAll(set2);
        //取完交集后，set1的值为{29}
        // 取最大
        result = Collections.max(set1);
        return result;
    }

    /**
     * 获取某一数值的所有因数
     */
    private static Set<Integer> getFactor(int m) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 2; i <= m; i++) {
            if (m % i == 0) {
                set.add(i);
            }
        }
        return set;
    }
    /**
     * 更相减损术
     * “可半者半之，不可半者，副置分母、子之数，以少减多，更相减损，求其等也。以等数约之。”
     * 第一步：任意给定两个正整数；判断它们是否都是偶数。若是，则用2约简；若不是则执行第二步。
     * 第二步：以较大的数减较小的数，接着把所得的差与较小的数比较，并以大数减小数。继续这个操作，直到所得的减数和差相等为止。
     * 两个正整数a和b（a>b），它们的最大公约数等于a-b的差值c和较小数b的最大公约数
     * 比如10和25，25减去10的差是15,那么10和25的最大公约数，等同于10和15的最大公约数
     * 【避免了大整数取模的性能问题】,但是其依靠两数求差的方式来递归，【运算次数大于辗转相除法】,比如计算10000和1，就要递归9999次
     */
    public static int equalGCD(int m, int n) {
        while (m != n) {
            if (m > n)
                m -= n;
            else
                n -= m;
        }
        return m;


    }

    public static void main(String[] args) {
//      int result = GCD(32, 48);
//      int result = PrimeGCD(32, 48);
        int result = equalGCD(32, 48);
        System.out.println(result);
    }
}
