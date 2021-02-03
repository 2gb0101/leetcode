package Array.Easy;

//Offer：给一个数组
//Target：问是否能找到一个至少是其他数字两倍的最大数字
public class D17_747_Largest_Number_At_Least_Twice_of_Others_1_bitand2_bitCharacters {
//    首先明确：
//    该数字一定是数组中的最大数字
//        且它是其他所有的数字的至少两倍
//        如果该数字是数组中第二大的数字至少两倍的话
//        那么它一定是其他所有数字的至少两倍
//    所以只遍历一次数组

//    注意这里判断两倍的方法：
//        并不是直接相除，为了避免除以零的情况，采用减法

//    暴力法：
//    遍历一遍数组找出最大数字
//    再遍历一遍数组
//    验证这个数字是否是其他数字的至少两倍
    public int dominantIndex1(int[] nums) {
        int mx = Integer.MIN_VALUE, mxId = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (mx < nums[i]) {
                mx = nums[i];
                mxId = i;
            }
        }
        for (int num : nums) {
            if (mx != num && mx - num < num) return -1;
        }
        return mxId;
    }
//    巧妙法：
//    遍历一次数组
//    分别求出最大数字和第二大数字
//    然后判断一下最大数字是否是第二大数字的两倍即可
    public int dominantIndex2(int[] nums) {
        int mx = Integer.MIN_VALUE, secondMx = Integer.MIN_VALUE, mxId = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] > mx) {
                secondMx = mx;
                mx = nums[i];
                mxId = i;
            } else if (nums[i] > secondMx) {
                secondMx = nums[i];
            }
        }
        return (mx - secondMx >= secondMx) ? mxId : -1;
    }
}
