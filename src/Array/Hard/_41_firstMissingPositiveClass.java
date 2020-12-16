package Array.Hard;

//offer:一个未排序的整数数组
//Target:找出其中没有出现的最小的正整数，时间复杂度应为O(n)，且只能使用常数级别的额外空间
public class _41_firstMissingPositiveClass {
//    解法：JAVA双指针，优化桶位
//    长度为N的无序数组在保证“无重复，无越界”的情况下，最多能装下N以内的正整数。
//    借用数组的[0, n-1]存储区间，试图有序的摆放[1, N]区间的正整数：
//    nums[i] 存储正整数 i + 1
//    如果出现重复，越界的情况，说明无效数字多占了一个坑位，丢掉无效数字的同时坑位减一。
    public int firstMissingPositive(int[] nums) {
        int res = 0; //坑位的下标
        int n = nums.length - 1; //坑位的下标的最大值
        int tmp = 0;
        while (res <= n) {
            if (nums[res] == res + 1) {
                res++;
            } else {
                tmp = nums[res];
                if (tmp > n + 1 || tmp < res + 1 || nums[tmp - 1] == tmp) { //无效数字
                    nums[res] = nums[n--];
                    //丢掉无效数字nums[res],同时坑位减一
                    //（nums[n]本身就是有值的，它也是nums数组的一部分）
                } else {
                    //把tmp放到nums数组中位置为tmp-1的槽上去
                    nums[res] = nums[tmp - 1];
                    nums[tmp - 1] = tmp;
                }
            }
        }
        return res + 1;
    }
}
