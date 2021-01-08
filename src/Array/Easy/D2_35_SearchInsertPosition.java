package Array.Easy;

//Offer：
//    有序数组
//    给定值
//Target：
//    返回给定值在数组中的下标
public class D2_35_SearchInsertPosition {
    //思路1:遍历原数组，进行数值比较即可
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    //思路2:二分搜索法来优化时间复杂度
    public int searchInsert1(int[] nums, int target) {
        if (nums[nums.length - 1] < target)
            return nums.length;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }
}
