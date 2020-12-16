package Array.Middle;

//Offer: 一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)
//Target: 求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数
public class D23_795_numSubarrayBoundedMax {
//    解法：计数
//    其实我们只关心数组中的元素是否小于 L，大于 R，或者位于 [L, R] 之间
//        假设一个元素小于 L 标记为 0，位于 [L, R] 之间标记为 1，大于 R 标记为 2
//        我们希望找出不包含 2 且至少包含一个 1 的子数组数量
//    因此可以看作是所有的 2 将数组拆分为仅包含 0 或 1 的子数组
//        例如在数组 [0, 0, 1, 2, 2, 1, 0, 2, 0]
//            2 将数组拆分为 [0, 0, 1]、[1, 0] 和 [0] 三个子数组
//    接下来，需要计算每个只包含 0 或 1 的数组中，至少包含一个 1 的子数组数量
//    问题可以转换为先找出所有的子数组，再从中减去只包含 0 的子数组
//
//    算法
//    假设 count(B) 用于计算所有元素都小于等于 B 的子数组数量
//    根据上面分析，本题答案为 count(R) - count(L-1)
//    那么如何计算 count(B)？
//        使用 cur 记录在 B 的左边，小于等于 B 的连续元素数量
//        当找到一个这样的元素时，在此位置上结束的有效子数组的数量为 cur + 1
//        当遇到一个元素大于 B 时，则在此位置结束的有效子数组的数量为 0
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return count(A, R) - count(A, L-1);
    }

    public int count(int[] A, int bound) {
        int ans = 0, cur = 0;
        for (int x: A) {
            cur = x <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
}
