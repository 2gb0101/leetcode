package Array.Easy;

import java.util.Arrays;

//Offer：给n个数字，但是0到n之间的数但是有一个数字去掉了
//Target：
//    寻找被去掉的数字
//    要求使用线性的时间复杂度和常数级的空间复杂度
public class D9_268_MissingNumber {

//    公式法 ：等差数列的求和公式
    public int missingNumber1(int[] nums) {
        int sum = 0, n = nums.length;
        for (int a : nums) {
            sum += a;
        }
        return n * (n + 1) / 2 - sum;
    }

//    位操作法（Bit Manipulation）：利用异或操作的特性
    public int missingNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
//            既然0到n之间少了一个数 ，那么将这个少了一个数的数组和0到n之间完整的数组异或一下
//            相同的数字都变为0了 ，剩下的就是少了的那个数字了

            //因为nums中存的是[0,n]的数据，且0 ^ 别的数 = 别的数本身
            //这里其实直接看成res为所有的nums[i] ^ i的结果即可
            res ^= (i + 1) ^ nums[i];
        }
        return res;
    }
//    二分查找法：排序的时间复杂度都不止O(n)，何必要多此一举用二分查找？
//    对，没错，但是在面试的时候，有可能人家给你的数组就是排好序的
//    那么此时用二分查找法肯定要优于上面两种方法
//    所以这种方法最好也要掌握一下
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) right = mid;
            else if(nums[mid] ==  mid) left = mid + 1;
            //因为在本题背景下，不可能存在nums[mid] <  mid，所以这种情况可以不讨论
        }
        return right;
    }
}
