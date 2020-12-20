package Array.Middle;

//Offer：
//    实现获取下一个排列的函数
//    算法需要将给定数字序列重新排列成字典序中下一个更大的排列
//Target：
//    如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）
//    必须原地修改，只允许使用额外常数空间
public class _31_NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 2, j = n - 1;
        //从数组后部往前找，如果数字一直是升序，就继续往前找
        while (i >= 0 && nums[i] >= nums[i + 1])
            --i;
        if (i >= 0) {
            //从数组后部往前找，找比nums[i]大的第一个数
            while (nums[j] <= nums[i])
                --j;
            swap(nums, i,j);
        }
        reverse(nums, i + 1);
    }

    //Java中不提供reverse函数的封装，所以要自己写
    //C++中是有的，在C++中可以直接调用reverse()函数
    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums,i, j);
            i++;
            j--;
        }
    }

    //注意，因为Java中int类型数据不能直接修改
    //所以下面这种写法是无法改变int数组中的数据的
//      private void swap(int i, int j) {
//          int temp = i;
//          i = j;
//          j = temp;
//      }

    //要这样写才行
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
