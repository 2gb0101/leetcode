package Array.Framework;

//二分查找
public class binarySearch {
//    框架
//    int binarySearch(int[] nums, int target) {
//        int left = 0, right = ...;
//
//        while(...) {
//            int mid = left + (right - left) / 2;
//            if (nums[mid] == target) {
//            ...
//            } else if (nums[mid] < target) {
//                left = ...
//            } else if (nums[mid] > target) {
//                right = ...
//            }
//        }
//        return ...;
//    }

//    技巧：
//    1.不要出现 else，把所有情况用 else if 写清楚，可以清楚地展现所有细节
//    2.计算 mid 时需要防止溢出
//      left + (right - left) / 2和(left + right) / 2的结果相同
//      但是有效防止了left和right太大直接相加导致溢出

//    示例1：寻找一个数（基本的二分搜索）
//    搜索一个数，如果存在，返回其索引，否则返回 -1
//    局限：给有序数组nums = [1,2,2,2,3]，target为 2，此算法返回的索引是 2
//    但是如果我想得到target的左/右侧边界，本算法无法处理
    int binarySearch(int[] nums, int target) {
//        因为我们初始化 right = nums.length - 1
//        所以决定了我们的「搜索区间」是 [left, right]
//        所以决定了 while (left <= right)
//            同时也决定了 left = mid+1 和 right = mid-1
//
//        因为我们只需找到一个 target 的索引即可
//        所以当 nums[mid] == target 时可以立即返回
        int left = 0;
        int right = nums.length - 1; // 注意

        //while循环有两种写法
        //写法1：
//        说明：此时right取值还是nums.length - 1
//        while(left < right) {
//            // ...
//        }
//        本写法的while循环，终止条件是left == right，搜索时会少一个索引left没被搜索，需要额外加语句判断
//        return nums[left] == target ? left : -1;

        //写法2：
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1; // 注意
//           有的是left = mid + 1，有的是left = mid
//           关键在于mid是否已经搜索过，如果是，就应该从搜索区间中去除
//           EG：「搜索区间」为[left, right]左闭右闭时，当nums[mid]被检测之后
//           下一步的搜索区间应该去掉mid分割成两个区间，即`[left, mid - 1]或[mid + 1, right)
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }

//    搜索左右边界的时候，其实最常用的是【左闭右开】的方式
//    示例2：寻找左侧边界的二分搜索
//  「左侧边界」的特殊含义：nums中小于 target 的元素有 x 个时，左侧边界为x
//   即x的取值区间是闭区间[0, nums.length]
    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索区间为 [left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // 搜索区间变为 [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // 搜索区间变为 [left, mid-1]
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 收缩右侧边界
                right = mid - 1;
            }
        }
        // 检查出界情况
        // target 比所有数都大
        if (left >= nums.length) return -1;
        // 类似之前算法的处理方式
        return nums[left] == target ? left : -1;
    }

    // 示例3：寻找右侧边界的二分查找
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
                //当`nums[mid] == target`时，不要立即返回
                // 而是增大「搜索区间」的下界`left`，使得区间不断向右收缩，达到锁定右侧边界的目的。
            }
        }
        // 这里改为检查 right 越界的情况，见下图
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }
}


