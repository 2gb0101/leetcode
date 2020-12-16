package Array.Middle;

//Offer: 一个长度为 n 的整数数组 nums，其中 n > 1
//Target: 返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
//
//提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
//说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
public class D16_238_productExceptSelf {
//    最简单
//        在线性时间和空间内解决
//        可以先计算给定数组所有元素的乘积，然后对数组中的每个元素 x，将乘积除以 x 来求得除自身值的以外的数组乘积。
//        问题：
//            如果输入数组中出现 0，那么这个方法就失效了
//            而且在问题中说明了不允许使用除法运算

//    方法一：左右乘积列表
//    我们不必将所有数字的乘积除以给定索引处的数字得到相应的答案，而是可以利用索引处左侧的所有数字乘积和右侧所有数字的乘积相乘得到答案。
//    对于给定索引 i，我们将使用它左边所有数字的乘积乘以右边所有数字的乘积
//
//    算法：
//    初始化两个空数组 L 和 R
//      对于给定索引 i
//      L[i] 代表的是 i 左侧所有数字的乘积
//            L[0] 应该是 1，因为第一个元素的左边没有元素
//            对于其他元素：L[i]=L[i-1]*nums[i-1]
//      R[i] 代表的是 i 右侧所有数字的乘积
//            R[length-1] 应为 1，length 指的是输入数组的大小
//            其他元素：R[i]=R[i+1]*nums[i+1]
//    当 R 和 L 数组填充完成，我们只需要在输入数组上迭代，且索引 i 处的值为：L[i]*R[i]。
    public int[] productExceptSelf1(int[] nums) {
        int length = nums.length;

        int[] L = new int[length];
        int[] R = new int[length];

        int[] answer = new int[length];

        L[0] = 1;
        for (int i = 1; i < length; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }

        R[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }

        for (int i = 0; i < length; i++) {
            answer[i] = L[i] * R[i];
        }

        return answer;
    }

//    解法2：空间复杂度 O(1) 的方法
//    尽管上面的方法已经能够很好的解决这个问题，但是不是常数的空间复杂度。
//    由于输出数组不算在空间复杂度内，那么我们可以将 L 或 R 数组在用输出数组来计算，然后再动态构造另一个
//    算法：
//    初始化 answer 数组，对于给定索引 i，answer[i] 代表的是 i 左侧所有数字的乘积。
//    构造方式与之前相同，只是我们试图节省空间
//    这种方法的唯一变化就是我们没有构造 R 数组。而是用一个遍历来跟踪右边元素的乘积。并更新数组 answer[i]=answer[i]∗R。然后 R 更新为 R=R∗nums[i]
    public int[] productExceptSelf2(int[] nums) {

        int length = nums.length;

        int[] answer = new int[length];

        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }
}
