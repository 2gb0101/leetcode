package Array.Hard;

//Offer: 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字
//Target: 你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
//        以任意顺序返回这两个数字均可
public class D10_1719_missingTwo {
//    解法：异或
//    首先确定N的值。易知：nums.length + 2就是N
//
//    接下来：nums数组的所有数 和 1~N的数 一同构成的集合
//    特征：该集合内只有2个数字出现过1次，而其余数字都出现2次，请找出这2个只出现1次的数字

//    先全部异或一次, 得到的结果, 我可以考虑异或结果的某个非 0 位，如最后一个非 0 位
//    因为只有当 num1、num2 在该位不一样的时候才会出现异或结果为 1.
//    所以我们以该 位是否为 1 对数组进行划分
//        只要该位为 1 就和 num1 异或
//        只要该位为 0就和 num2 异或
//         (其他在该位为 1 或 0 的数必然出现 0/2 次对异或结果无影响)
//    这样最终得到就是只出现过一次的两个数
    public int[] missingTwo(int[] nums) {
//        异或的特点：
//        一个数异或同一个数两次，结果还是那个数
//        相同的数异或结果都为 0
//        0和任意数异或结果都为那个数
        int xor = 0, idx = 0;
        for (int num : nums) {
            xor ^= num;
            xor ^= ++idx;
        }
        xor ^= ++idx;
        xor ^= ++idx;
        //上面的计算走完后，xor=num1^num2（其他的num和idx，相同的互相消掉了）
        int diff = xor & (-xor);
//        -xor之后的二进制 = xor所有位取反 + 1，说白了就是补码
//        xor & (-xor) 可以得到一串二进制数，其中只有一个非 0 位
//        因为只有当 num1、num2 在该位不一样的时候才会出现异或结果为 1
//        而仔细分析一下，xor & (-xor)的结果的非零位

        int x = 0;
        idx = 0;
        for (int num : nums) {
            if ((diff & num) != 0)
                x ^= num;
            if ((diff & ++idx) != 0)
                x ^= idx;
        }
        if ((diff & ++idx) != 0)
            x ^= idx;
        if ((diff & ++idx) != 0)
            x ^= idx;
        return new int[]{x, xor ^ x};
    }
}
