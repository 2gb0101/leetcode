package Array.Easy;

//Offer：给一个数组
//Target：最多有1次修改某个数字的机会，问能不能将数组变为非递减数组
public class D15_665_NonDecreasingArray {
    public boolean checkPossibility(int[] nums) {
//        自己写几个例子，可发现后面的数字小于前面的数字
//            有时需要修改前面较大的数字
//            有时候却要修改后面较小的那个数字
//        那么有什么内在规律吗？
//            有的，判断修改哪个数字其实跟再前面一个数的大小有关系
//            首先如果再前面的数不存在
//                如【4，2，3】4前面没有数字了
//                我们直接修改前面的数字为当前的数字2即可
//            当再前面的数字存在，并且小于当前数时
//                如【-1，4，2，3】，-1小于2
//                需要修改前面的数字4为当前数字2
//            如果再前面的数大于当前数
//                如【2，3，3，2，4】，3大于2
//                需要修改当前数2为前面的数3
        int cnt = 1, n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i] < nums[i - 1]) {
                if (cnt == 0) return false;
                if (i == 1 || nums[i] >= nums[i - 2]) nums[i - 1] = nums[i];
                else nums[i] = nums[i - 1];
                --cnt;
            }
        }
        return true;
    }
}
