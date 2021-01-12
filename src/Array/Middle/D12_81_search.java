package Array.Middle;

//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//(例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
//编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
//本题中的 nums  可能包含重复元素
public class D12_81_search {
//    本题是需要使用二分查找，怎么分是关键，举个例子：
//    第一类：分不清到底是前面有序还是后面有序
//            如10111 和 11101 ，nums[start] == nums[mid]
//            此时 start++ 即可。相当于去掉一个重复的干扰项。
//    第二类：前半部分有序
//        如 2 3 4 5 6 7 1，nums[start] < nums[mid]，此例子中就是 2 < 5；
//        如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
//    第三类：后半部分有序
//        如 6 7 1 2 3 4 5， nums[start] > nums[mid]，此例子中就是 6 > 2；
//        如果 nums[mid] <target<=nums[end]。则在后半部分找，否则去前半部分找。
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {  //否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;
                }
            }
        }
        //一直没找到，返回false
        return false;

    }
}
