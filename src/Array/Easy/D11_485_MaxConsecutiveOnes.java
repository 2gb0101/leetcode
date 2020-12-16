package Array.Easy;

//Offer：给定一个二进制数组
//Target：找到此数组中最多有多少个连续的1
public class D11_485_MaxConsecutiveOnes {
//    遍历法：
//    遍历一遍数组，用一个计数器cnt来统计1的个数
    public int findMaxConsecutiveOnes1(int[] nums) {
        int res = 0, cnt = 0;
        for (int num : nums) {
            cnt = (num == 0) ? 0 : cnt + 1;
            res = Math.max(res, cnt);
        }
        return res;
    }
//    二进制特点法：
//    由于是个二进制数组，所以数组中的数字只能是0或1
//    那么连续1的和跟个数相等，所以我们可以计算和
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0, sum = 0;
        for (int num : nums) {
            sum = (sum + num) * num;
            res = Math.max(res, sum);
        }
        return res;
    }
}


