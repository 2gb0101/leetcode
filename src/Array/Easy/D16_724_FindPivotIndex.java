package Array.Easy;

//Offer：一个数组
//Target：求一个中枢点，使得该位置左右两边的子数组之和相等
public class D16_724_FindPivotIndex {
//    因为中枢点可能出现的位置就是数组上的位置
//        所以搜索一遍就可以找出来
//    先求出数组的总和
//    再维护一个当前数组之和curSum
    public int pivotIndex(int[] nums) {
        int curSum = 0, n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
        }
        for (int i = 0; i < n; ++i) {
            if (sum - nums[i] == 2 * curSum) return i;
            curSum += nums[i];
        }
        return -1;
    }
}
