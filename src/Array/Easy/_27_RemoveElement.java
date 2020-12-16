package Array.Easy;
//Offer：
//    一个数组
//    给定值
//Target：
//    移除数组中和给定值相同的数字
//    返回新数组的长度
//    不得申请新数组帮助解决问题
public class _27_RemoveElement {
//    思路：使用指针遍历即可
    public int removeElement(int[] nums, int val) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != val)
                nums[res++] = nums[i];
        }
        return res;
    }
}
