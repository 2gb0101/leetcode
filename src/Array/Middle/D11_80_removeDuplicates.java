package Array.Middle;

//Offer: 一个排序数组
//Target: 在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度
//不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成
public class D11_80_removeDuplicates {
//    解法：指针法
    public int removeDuplicates1(int[] nums) {
        int m = 0;
        for (int i = 0; i < nums.length;) {
            // 当有两个连续的数字重复的时候就要进行判断（因为从题目已知，这个数组是有序的）
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                // 先保存当前val
                int val = nums[i];
                // 然后再复制两个重复的元素
                nums[m++] = nums[i++];
                nums[m++] = nums[i++];
                // 接着指针i开始跳过多余的重复元素即可
                while (i < nums.length && nums[i] == val)
                    i++;
            } else
                nums[m++] = nums[i++];
        }
        return m;
    }

    public int removeDuplicates2(int[] nums) {
        if(nums.length < 3){
            return nums.length;
        }
        // 用一个标识curPos记录当前结尾的位置。
        int curPos = 1;
        for (int i = 2; i < nums.length ; i++) {
            // 如果遍历到的数nums[i]和nums[curPos-1]相等。说明nums[i]==nums[curPos]==nums[curPos-1]。
            // 因此已经连续三个数相等了。nums[i]直接忽略即可。
            // 如果nums[i]和nums[curPos-1]不相等。则应将其赋值到curPos+1的位置。
            // 遍历结束以后。curPos+1就是需要求的新长度。
            if(nums[i] != nums[curPos-1]){
                nums[++curPos] = nums[i];
            }
        }
        return curPos+1;
    }
}
