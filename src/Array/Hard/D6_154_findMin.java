package Array.Hard;

//Offer:假设按照升序排序的数组在预先未知的某个点上进行了旋转
//( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )
//Target:请找出其中最小的元素,数组中可能存在重复的元素
public class D6_154_findMin<二分法> {
    //    解法1：二分法
//    说明：这种方式，保证了返回的一定是最小值，但是不一定是旋转点。例如：[1,1,1,3,1]
//    nums[mid] > nums[right]
//      mid在大区间里，left=mid+1
//    nums[mid] > nums[right]
//      mid在小区间里，right = mid
//    nums[mid] = nums[right]
//      本题难点，此题数组元素可重复，难以判断分界点i指针区间
//      采用right = right - 1解决该问题
//            此操作不会使数组越界：因为迭代条件保证了right > left >=0
//            此操作不会使最小值丢失：假设nums[right]是最小值，有两种情况：
//                若nums[right]是唯一最小值：那不可能满足判断条件nums[mid] == nums[right]，因为mid < right（left != right且 mid = (left+right)//2向下取值）
//                若nums[right]不是唯一最小值：由于mid < right而nums[mid] == nums[right]，即还有最小值存在于[left,right-1]区间
    public int findMin0(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
            else if (nums[mid] == nums[right])
                right = right - 1;
        }
        return nums[left];
    }

//    优化：在中间值等于右边界值是，做了 2 次判断，针对该题解进行点优化。
//    如果是[0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1] 这情况情况
//    虽然中间值等于右边，但是中间值小于左边，说明【要找的值】肯定在左半部分
//    题解里面如果相等直接减 1 ，这一处可以进行优化。能少减几个是几个，万一有 1 亿 个呢
    public int findMin1(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int length = nums.length - 1;
        if (nums[length] > nums[0]) return nums[0]; // 如果已经有序

        int left = 0, right = length;
        while (left < right) {
            // 如果 nums[left] < nums[right] 表示有序返回 nums[0] 即可
            if (nums[left] < nums[right]) return nums[left];
            int mid = (left + right) >> 1;
            int num = nums[mid];
            // 如果中间值大于等于右边值，说明反转部分在右边，否则在左边
            if (num > nums[right]) left = mid + 1;
            else if (num < nums[right]) right = mid;
            else {
                // 如果等于中间值，不确定左右时，比如 [1,1,0,1,1,1,1,1] 这种情况，我们只能随机缩减另一边
                if (num == nums[left]) right--;
                else right = mid; // 在左半部分
            }
        }
        return nums[left];
    }

//    解法2：分治法
    public int findMin2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            throw new IllegalArgumentException("数组为空，最小值不存在");
        }
        return findMin(nums, 0, len - 1);
    }

    private int findMin(int[] nums, int left, int right) {
        if (left + 1 >= right) {
            return Math.min(nums[left], nums[right]);
        }
        if (nums[left] < nums[right]) {
            //一定是在小区间里，所以直接返回nums[left]即可
            return nums[left];
        }
        // int mid = left + (right - left) / 2;
        int mid = (left + right) >>> 1;
        return Math.min(findMin(nums, left, mid - 1), findMin(nums, mid, right));
    }
}
