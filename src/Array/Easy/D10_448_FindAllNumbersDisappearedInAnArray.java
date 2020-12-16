package Array.Easy;

import java.util.ArrayList;
import java.util.List;

//Offer：一个给定数组，数组元素满足 1 ≤ a[i] ≤ n，其中某些元素出现了一次，某些出现两次
//Target：找出数组中所有消失的数
public class D10_448_FindAllNumbersDisappearedInAnArray {
//    这道题让找不存在的数
//    这类问题的一个重要条件就是1 ≤ a[i] ≤ n (n = size of array)
//    不然很难在O(1)空间和O(n)时间内完成
//
//    下标法：
//    利用数组的下标和数组中的数的关系，查找消失的数
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            int idx = Math.abs(nums[i]) - 1;
            nums[idx] = (nums[idx] > 0) ? -nums[idx] : nums[idx];
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > 0) { //出现次数不为1次的元素，一定是正数
                res.add(i + 1);
            }
        }
        return res;
    }
//    位置交换法
//    该法在leetcode中超出时间限制了，就当扩展思路吧
//            先将现在的数组按照所有数都存在时的顺序排好
//    排好序后检查下标和对应位置的数是否一致
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int tmp;
        for (int i = 0; i < nums.length; ++i) {
            //这里的nums[i]代表的就是数组的index下标
            if (nums[i] != nums[nums[i] - 1]) { //把nums[i]放到它应该放的位置（下标为nums[i] - 1的位置）上去
                //下面这三句相当于
                //swap(nums[i], nums[nums[i] - 1]);
                tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[nums[i] - 1] = tmp;

                --i;
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }
//     累加观察法
//    在nums[nums[i]-1]位置累加数组长度n
//    要找出缺失的数，只需要看nums[i]的值是否小于等于n即可
    public List<Integer> findDisappearedNumbers3(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            nums[(nums[i] - 1) % n] += n;
            //不能把(nums[i] - 1) % n 写成 (nums[i] % n) - 1
            //因为会出现java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 8
//            -1 % 2 = -1
//            -2 % 2 = 0
//            -3 % 2 = -1

        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
