package Array.Middle;

//Offer：给定一个按照升序排列的整数数组 nums
//Target：
//    让我们在一个有序整数数组中寻找相同目标值的起始和结束位置
//    而且限定了时间复杂度为 O(logn)
//    如果数组中不存在目标值，返回 [-1, -1]
//思路：
//    O(logn)是典型的二分查找法的时间复杂度
//    所以这里也需要用此方法
public class D4_34_FindFirstandLastPositionofElementinSortedArray {
    //    解法1：单次二分法（伪O(logn）：
//        对原数组使用二分查找法
//        找出其中一个目标值的位置
//        然后向两边搜索找出起始和结束的位置
//        可能有些人会觉得该算法不是严格意义上的 O(logn) 的算法
//        因为在最坏的情况下会变成 O(n)，比如当数组里的数全是目标值的话，从中间向两边找边界就会一直遍历完整个数组
    public int[] searchRange1(int[] nums, int target) {
        int idx = search(nums, 0, nums.length - 1, target);
        if (idx == -1)
            return new int[]{-1, -1};
        int left = idx, right = idx;
        while (left > 0 && nums[left - 1] == nums[idx])
            --left;
        while (right < nums.length - 1 && nums[right + 1] == nums[idx])
            ++right;
        return new int[]{left, right};
    }

    int search(int[] nums, int left, int right, int target) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] < target)
            return search(nums, mid + 1, right, target);
        else return search(nums, left, mid - 1, target);
    }

//    解法2：双次二分法：
//        第一次找到左边界
//        第二次调用找到右边界即可
    public int[] searchRange2(int[] nums, int target) {
        int[] res = {-1, -1};
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else
                //这里的else其实隐含了nums[mid] >= target的情况
                //当nums[mid] = target时，如果left < right，并不会退出循环
                //当nums[mid] = target时，还继续走循环，其实就是在找第一次找到target值的位置
                //退出循环的可能情况就是：right指针不断向左移，直至right=left
                //此时right指针就找到了target值第一次出现的位置
                right = mid;
        }
        if (right == nums.length || nums[right] != target)
            return res;

        res[0] = right;
        right = nums.length;
        //此时left就是第一次二分查找后，找到的target值第一次出现的位置
        while (left < right) {
            int mid = left + (right - left) / 2;
            //当nums[mid] = target时，如果left < right，并不会退出循环
            //当nums[mid] = target时，还继续走循环，其实就是在找最后一次找到target值的位置
            //退出循环的可能情况就是：left指针不断向右移，直至right=left
            //此时right指针就找到了target值最后一次出现的位置
            if (nums[mid] <= target)
                left = mid + 1;
            else
                //这里的else隐含的就是nums[mid] > target
                //所以当循环结束的时候，left保存的一定是比mid大的数字中最小的那个
                right = mid;
        }
        //因为left保存的一定是比mid大的数字中最小的那个
        //所以target值最后一次出现的时候是在left - 1的位置
        res[1] = left - 1;
        return res;
    }

//    解法3：单次二分法（真O(logn)）
//    只使用一个二分查找的子函数，来同时查找出第一个和最后一个位置
//    如何只用查找第一个大于等于目标值的二分函数来查找整个范围呢
//    这里用到了一个小 trick
//    首先来查找起始位置的 target
//    就是在数组中查找第一个大于等于 target 的位置
//    当返回的位置越界，或者该位置上的值不等于 target 时
//    表示数组中没有 target，直接返回 {-1, -1} 即可
//    若查找到了 target 值
//    则再查找第一个大于等于 target+1 的位置
//            然后把返回的位置减1
//    就是 target 的最后一个位置
//    即便是返回的值越界了，减1后也不会越界
//            这样就实现了使用一个二分查找函数来解题啦
}
