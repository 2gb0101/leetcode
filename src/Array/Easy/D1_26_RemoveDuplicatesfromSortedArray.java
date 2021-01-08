package Array.Easy;

//Offer：
//    有序数组（色块模型）
//Target：
//    返回一个数字，说明数组中一共有多少个不同的数（但是leetcode上又要求返回数组）
//    （要求剔除数组中重复的色块）
//    不得申请新数组帮助解决问题
public class D1_26_RemoveDuplicatesfromSortedArray {

//----思路一：快慢标识
//    最开始时两个标识都指向第一个数字
//    如果两个标识指的数字
//        相同-->快标识向前走一步
//        不同-->两个标识都向前走一步
//    当快标识走完整个数组后
//    数组中不同数字的个数 = 慢标识当前的坐标加1

//----思路二：覆盖数组
//    由于题目提到，可以更改数组的值
//    且假设已知数组中有A个不同的数字
//    下标超过A-1的数组值为多少，题目并不限制你修改
//    所以：一边循环，一边用后面没有重复过的数字覆盖掉前面数组中的数
//    得到新数组，数组的前A个数就是数组中出现的A个不同的数


    //①指针法：
    //    数组可以通过下标访问值
    //    使用快慢指针作为标识，来记录遍历的坐标
    public int removeDuplicates1(int[] nums) {
        int pre = 0, cur = 0, n = nums.length;
        while (cur < n) {
            if (nums[pre] == nums[cur])
                cur++;
            else
                nums[++pre] = nums[cur++];
        }
        return pre + 1;
    }

    //②for循环法：
    //    把一层for循环的变量i作为快标识
    //            另起一个标识作为慢标识
    public int removeDuplicates2(int[] nums) {
        int j = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] != nums[j])
                nums[++j] = nums[i];
        }
        return j + 1;
    }

    //③单变量法
    //    用变量i表示当前覆盖到的位置
    //    把当前数字 num 跟上一个覆盖到数字 nums[i] 做比较
    //    num 大，一定不会有重复（前提是数组必须有序）
    //    否则i++
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for(int num : nums) {
            if(num > nums[i]) {
                nums[++i] = num;
            }
        }
        return i + 1;
    }
}
