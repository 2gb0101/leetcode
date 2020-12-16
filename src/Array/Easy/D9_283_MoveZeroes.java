package Array.Easy;
//Offer：一个数组
//Target：
//    将数组中所有的0都移到后面，把非零数前移
//    不能改变非零数的相对应的位置关系，不能将数据拷贝额外的数组
public class D9_283_MoveZeroes {
//    替换法in-place(快慢指针)
//    由于不能将数据拷贝额外的数组，那么考虑用替换法来做
    public void moveZeroes(int[] nums) {
        int tmp = 0, j = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                //下面的写法其实就是：swap(nums[i], nums[j++]);
                tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                j++;
            }
        }
    }
}
