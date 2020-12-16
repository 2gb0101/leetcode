package Array.Middle;

import java.util.ArrayList;
import java.util.List;

//Offer: 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次
//Target: 找到所有出现两次的元素
//        你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
public class D18_442_findDuplicates {

//    解法1：“抽屉原理” + 基于“异或运算”交换两个变量的值
//
//    特别注意：基于“异或运算”交换两个变量的值，这种操作仅仅只是用于解题，千万不要在工作中使用这样的代码，会有一些坑
//    重点是：使得代码难以阅读和被他人理解没有必要为了节约空间去牺牲代码的可读性
//
//    思路分析：
//    这里由于数组元素限定在数组长度的范围内，因此，我们可以通过一次遍历：
//        让数值 1 就放在索引位置 0 处；
//        让数值 2 就放在索引位置 1 处；
//        让数值 3 就放在索引位置 2 处；
//        ……
//    一次遍历以后，那些“无处安放”的元素就是我们要找的“出现两次的元素”
//    为了不使用额外的空间，这里使用到的一个技巧是“基于异或运算交换两个变量的值”
//        交换两个整数，除了引入一个新的变量，写出一个“轮换”的赋值表达式以外
//        还有不使用第三方变量的2种方法
//        先知道：如果a^b=c，则a^c=b和b^c=a同时成立
//        法1：基于异或运算（好记，但注意需要额外判断自己和自己交换的情况，因为i^i=0）
//            a = a ^ b
//            b = a ^ b
//            a = a ^ b
//        法2：基于加减法（但这种方法可能会导致溢出）
//            a=a+b
//            b=a-b
//            a=a-b

    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        for (int i = 0; i < len; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
//              注意：因为这里数组的数值和索引有一个偏差，所以我将交换数组元素的方法做了一个封装，这样可以降低编码出错的概率，供大家参考
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

//  调用 swap 方法使用了栈空间，但是如果不这么写，代码很容易出错，也不符合编码规范，我个人觉得知道这个知识点就可以了
    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

//    解法2：负数反转
//    找到数字i时，将位置i-1处的数字翻转为负数
//    如果位置i-1 上的数字已经为负，则i是出现两次的数字
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            int index = Math.abs(nums[i])-1;
            if (nums[index] < 0)
                res.add(Math.abs(index+1));
            nums[index] = -nums[index];
        }
        return res;
    }
}
