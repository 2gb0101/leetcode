package Array.Middle;

//Offer：
//    假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//    (例如，数组[0,1,2,4,5,6,7]可能变为[4,5,6,7,0,1,2])。
//Target：
//    搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1
//    你可以假设数组中不存在重复的元素。
//    你的算法时间复杂度必须是O(logn)级别
public class D3_33_SearchInRotatedSortedArray {
//    考虑二分搜索法
//    二分搜索法的关键在于
//            获得了中间数后
//        判断下面要搜索左半段还是右半段
//        画图可以得出出规律
//                如果中间的数小于最右边的数
//        则右半段是有序的
//                若中间数大于最右边数
//        则左半段是有序的
//                只要在有序的半段里用首尾两个数组来判断目标值是否在这一区域内
//        这样就可以确定保留哪半边了
//    解法1：num[mid]与num[right]比较大小
    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            //如果中间的数小于最右边的数,则右半段是有序的
            if (nums[mid] < nums[right]) {
                //如果target在nums[mid]和nums[right]之间，那么说明继续在右半部分找
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else //否则就在左半部分找
                    right = mid - 1;
            } else { //若中间数大于最右边数,则左半段是有序的
                if (nums[left] <= target && nums[mid] > target)
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return -1;
    }

//    解法2：num[mid]与num[left]比较大小
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && nums[mid] > target)
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (nums[mid] < target && nums[right] >= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}
