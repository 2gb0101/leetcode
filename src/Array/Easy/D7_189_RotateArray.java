package Array.Easy;

import Utils.Array.Utils_Array_Reverse;
import org.junit.Test;

import java.util.Arrays;

//Offer：一个数组，一个数字K
//Target：
//将数组左边的子数组绕着数组中最后一个数
//旋转到数组右边去，一共旋转K次
public class D7_189_RotateArray {
//    思路1：将 nums[idx] 上的数字移动到 nums[(idx+k) % n] 上去

//    解法1：借助新数组
//    空间复杂度：O(n) 的空间复杂度
    public void rotate1(int[] nums, int k) {
        //注意，这里容易写错成int[] t = nums;
        //如果写错，那么t和nums指向同一个数组，里面的数会一起被改变的
        //但解法一的逻辑是，t和nums是不同的数组，只是把nums里的数据拷贝一份到t中而已
        int[] t = nums.clone();
        for (int i = 0; i < nums.length; ++i) {
            nums[(i + k) % nums.length] = t[i];
        }
    }

//    解法2：暴力法
//    提示中要求空间复杂度为 O(1)，这样就不能用辅助数组
//    写法却复杂的多，需要用到很多辅助变量
    public void rotate2(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) return;
        //为了防止数据覆盖丢失，需要用额外的变量来保存，这里用了 pre 和 cur
        int start = 0, idx = 0, pre = 0, cur = nums[0], n = nums.length;
        //因为长度为n的数组只需要更新n次，所以用一个 for 循环来处理n次
        for (int i = 0; i < n; ++i) {
            pre = cur; //将 pre 更新为 cur
            idx = (idx + k) % n; //计算新的 idx 的位置
            cur = nums[idx]; //将 nums[idx] 上的值先存到 cur 上
            nums[idx] = pre; //把 pre 赋值给 nums[idx]
            //相当于把上一轮的 nums[idx] 赋给了新的一轮，完成了数字的交换

            //if 语句判断是否会变到处理过的数字
            //为了防止陷入死循环，还需要一个变量 start，初始化为0
            if (idx == start) {
                //一旦当 idx 变到了 strat 的位置
                //则 start 自增1，再赋值给 idx
                //这样 idx 的位置也改变了，可以继续进行交换了
                idx = ++start;
                cur = nums[idx];
            }
        }
    }

    @Test
    public void test(){
        int[] nums = new int[]{1,2,3,4,5,6,7};
        new D7_189_RotateArray().rotate3(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
//    解法3：类似翻转字符法
//    先把前 n-k 个数字翻转一下
//    再把后k个数字翻转一下
//    最后再把整个数组翻转一下
    public void rotate3(int[] nums, int k) {
        if (nums.length == 0 || (k %= nums.length) == 0) return;
        int n = nums.length;
        Utils_Array_Reverse.reverse(nums, 0, 0 + n - k);
        Utils_Array_Reverse.reverse(nums, 0 + n - k, nums.length);
        Utils_Array_Reverse.reverse(nums, 0, nums.length);
    }
//  注意，这里的数组元素逆序everse方法需要自己写，java是没有提供相关方法的（C++有）

//    解法4：C++，数字旋转法

//    解法5：逻辑法
//    旋转 k 次，每次将数组旋转 1 个元素
//    复杂度分析
//    时间复杂度：O(n*k),每个元素都被移动 1步（O(n)）k次（O(k)） 。
//    空间复杂度：O(1),没有额外空间被使用
    public void rotate(int[] nums, int k) {
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }
}
