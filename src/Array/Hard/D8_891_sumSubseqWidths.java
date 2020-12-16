package Array.Hard;

import java.util.Arrays;

//Offer：一个整数数组 A ，考虑 A 的所有非空子序列。
//Target:
//    返回 A 的所有子序列的宽度之和
//    对于任意序列 S ，设 S 的宽度是 S 的最大元素和最小元素的差
//    由于答案可能非常大，请返回答案模 10^9+7
public class D8_891_sumSubseqWidths {
//    解法：数学分析
//    每个非空子序列，只关注最大/最小元素，所以对数组排序，方便处理
//    这里的2 ^ j - 1要理解成（2）^ （j - 1），即2的（j - 1）次方
//    用 (i, j) 表示固定两端的序列，它的最小值为 a[i]，最大值为 a[j]，这样的序列有 2 ^ j - i - 1 个
//    (因为数组里最少要有两个元素，才存在最大/最小元素，此时才可以计算序列，因此为 j - i - 1)
//    每个序列的宽度为 a[j] - a[i]，这一组序列的总宽度为 (a[j] - a[i]) * (2 ^ j - i - 1)
//    此时可以在 O(n^2) 的时间复杂度内解决问题

//    但是数据范围达到了 20000， O(n^2) 的算法不可取。要想办法降低时间复杂度。
//    对于以 a[j] 结尾的序列，它的总宽度为：
//    result = (a[j] - a[0]) * (2 ^ j - 1) + (a[j] - a[1]) * (2 ^ j - 2) + ... + (a[j] - a[j - 1]) * (2 ^ 0)。
//
//    可表示为 result = X - Y，其中
//            X = a[j] * (2 ^ j - 1 + 2 ^ j - 2 + ... + 2 ^ 0) = a[j] * ((2 ^ j) - 1)。
//
//            （用二进制角度想就很好想通：(2 ^ j - 1 + 2 ^ j - 2 + ... + 2 ^ 0) = (2 ^ j) - 1）
//    Y = a[0] * 2 ^ j - 1 + a[1] * 2 ^ j - 2 + ... + a[j - 1] * 2 ^ 0。
//
//    X 可以在常量时间内得到，Y 不行，但是可以知道 Y[j + 1] = Y[j] * 2 + a[j]
//    有了这个递推表达式，每个 Y 也可以在遍历过程中常数时间得到。

//    在计算过程中 res 可能为负，为了保证得到正确的结果，最后进行一次 (res + MOD) % MOD 这个操作，保证结果为正。
//    算法时间复杂度为 O(nlogn)，主要花费在排序上，排序完成后只需要 O(n) 的扫描。
    public int sumSubseqWidths1(int[] A) {
        final int MOD = (int) (1e9 + 7);
        Arrays.sort(A);
        int n = A.length;
        long res = 0;
        long p = 1;
//      result = X - Y，其中 X = a[j] * ((2 ^ j) - 1) = a[j] * (p - 1) ,即设 p = 2 ^ j
//      Y[j + 1] = Y[j] * 2 + a[j]
        long Yj1 = 0, Yj = 0;
        for(int j = 0; j < A.length; j++){
            res = (res + A[j] * (p - 1) - Yj1) % MOD;
            p = (p << 1) % MOD; //相当于p = p * 2
            Yj = Yj1;
            Yj1 = (Yj * 2 + A[j]) % MOD;
        }
        return (int) ((res + MOD) % MOD);
    }

    public int sumSubseqWidths2(int[] A) {
        final int MOD = (int) (1e9 + 7);
        Arrays.sort(A);
        int n = A.length;
        long res = 0;
        long p = 1;
        for (int i = 0; i < n; ++i) {
            res = (res + (A[i] - A[n - 1 - i]) * p) % MOD;
            p = (p << 1) % MOD;
        }
        return (int) ((res + MOD) % MOD);
    }
}
